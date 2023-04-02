package io.github.notoday.sharding.sphere.data.migrate.support;

import io.github.notoday.sharding.sphere.data.migrate.config.ApplicationProperties;
import io.github.notoday.sharding.sphere.data.migrate.service.DataMigration;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.encrypt.api.encrypt.like.LikeEncryptAlgorithm;
import org.apache.shardingsphere.encrypt.api.encrypt.standard.StandardEncryptAlgorithm;
import org.apache.shardingsphere.encrypt.spi.EncryptAlgorithm;
import org.apache.shardingsphere.encrypt.yaml.config.YamlEncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.yaml.config.rule.YamlEncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.yaml.config.rule.YamlEncryptTableRuleConfiguration;
import org.apache.shardingsphere.infra.util.spi.type.typed.TypedSPILoader;
import org.apache.shardingsphere.infra.util.yaml.constructor.ShardingSphereYamlConstructor;
import org.apache.shardingsphere.infra.yaml.config.pojo.YamlRootConfiguration;
import org.apache.shardingsphere.infra.yaml.config.pojo.rule.YamlRuleConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author no-today
 * @date 2023/03/31 09:10
 */
@Slf4j
@Component
public class ShardingEncryptRuleConfigReader {

    private final Map<String, LikeEncryptAlgorithm> likeEncryptors = new LinkedHashMap<>();
    private final Map<String, StandardEncryptAlgorithm> standardEncryptors = new LinkedHashMap<>();

    private final ApplicationProperties applicationProperties;

    public ShardingEncryptRuleConfigReader(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void process(DataMigration dataMigration) {
        YamlEncryptRuleConfiguration yamlEncryptRuleConfiguration;
        try {
            yamlEncryptRuleConfiguration = loadEncryptRuleConfiguration(applicationProperties.getShardingConfig());
            assert yamlEncryptRuleConfiguration != null;
        } catch (IOException e) {
            log.error("Load sharding config fail: ", e);
            return;
        }

        List<EncryptTableRuleConfig> configs = mapper(yamlEncryptRuleConfiguration);

        configs.forEach(e -> safeRun(e.getTableName(), () -> dataMigration.process(e)));
    }

    private void safeRun(String tableName, Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            log.error("[数据迁移-异常] table: {}", tableName, e);
        }
    }

    private List<EncryptTableRuleConfig> mapper(YamlEncryptRuleConfiguration yamlEncryptRuleConfiguration) {
        // 核心逻辑: TypedSPILoader 加载加密算法
        yamlEncryptRuleConfiguration.getEncryptors().forEach((key, value) -> putAllEncryptors(key, TypedSPILoader.getService(EncryptAlgorithm.class, value.getType(), value.getProps())));

        return yamlEncryptRuleConfiguration.getTables().keySet().stream().map((tableName) -> {
            YamlEncryptTableRuleConfiguration table = yamlEncryptRuleConfiguration.getTables().get(tableName);

            List<EncryptColumnRuleConfig> columns = table.getColumns().keySet().stream().map((columnName) -> {
                YamlEncryptColumnRuleConfiguration column = table.getColumns().get(columnName);

                return new EncryptColumnRuleConfig()
                        .setTableName(tableName)
                        .setColumn(columnName)
                        .setCipherColumn(column.getCipherColumn())
                        .setAssistedQueryColumn(column.getAssistedQueryColumn())
                        .setLikeQueryColumn(column.getLikeQueryColumn())
                        .setCipherEncryptor(standardEncryptors.get(column.getEncryptorName()))
                        .setAssistedQueryEncryptor(standardEncryptors.get(column.getAssistedQueryEncryptorName()))
                        .setLikeQueryEncryptor(likeEncryptors.get(column.getLikeQueryEncryptorName()));
            }).collect(Collectors.toList());

            return new EncryptTableRuleConfig()
                    .setTableName(tableName)
                    .setColumns(columns);
        }).collect(Collectors.toList());
    }

    private YamlEncryptRuleConfiguration loadEncryptRuleConfiguration(String yamlConfigPath) throws IOException {
        try (InputStream inputStream = new ClassPathResource(yamlConfigPath).getInputStream()) {
            YamlRootConfiguration yamlRootConfiguration = new Yaml(new ShardingSphereYamlConstructor(YamlRootConfiguration.class)).loadAs(inputStream, YamlRootConfiguration.class);
            for (YamlRuleConfiguration yamlRuleConfiguration : yamlRootConfiguration.getRules()) {
                if (yamlRuleConfiguration instanceof YamlEncryptRuleConfiguration) {
                    return (YamlEncryptRuleConfiguration) yamlRuleConfiguration;
                }
            }
        }

        return null;
    }

    private void putAllEncryptors(final String encryptorName, final EncryptAlgorithm algorithm) {
        if (algorithm instanceof StandardEncryptAlgorithm) {
            standardEncryptors.put(encryptorName, (StandardEncryptAlgorithm) algorithm);
        } else {
            likeEncryptors.put(encryptorName, (LikeEncryptAlgorithm) algorithm);
        }
    }
}
