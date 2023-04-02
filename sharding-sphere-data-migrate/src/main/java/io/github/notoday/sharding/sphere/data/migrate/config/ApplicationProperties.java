package io.github.notoday.sharding.sphere.data.migrate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author no-today
 * @date 2023/03/28 14:25
 */
@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {

    /**
     * 加密字段长度
     * <p>
     * VARCHAR(255)
     */
    private int encryptedFieldLength = 255;

    /**
     * 每批查询数量
     */
    private int batchSize = 1000;

    /**
     * Sharding 配置文件路径
     */
    private String shardingConfig = "sharding.yaml";

    /**
     * 迁移SQL脚本输出路径
     */
    private String outputSqlScriptDir = "./sql-scripts";
}
