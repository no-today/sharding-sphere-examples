package io.github.notoday.sharding.sphere.data.migrate;

import io.github.notoday.sharding.sphere.data.migrate.config.ApplicationProperties;
import io.github.notoday.sharding.sphere.data.migrate.service.DataMigration;
import io.github.notoday.sharding.sphere.data.migrate.support.ShardingEncryptRuleConfigReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class ShardingSphereDataMigrateApplication implements ApplicationListener<ApplicationReadyEvent> {

    private final ShardingEncryptRuleConfigReader configReader;
    private final DataMigration dataMigration;

    public ShardingSphereDataMigrateApplication(ShardingEncryptRuleConfigReader configReader, DataMigration dataMigration) {
        this.configReader = configReader;
        this.dataMigration = dataMigration;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        configReader.process(dataMigration);
    }

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereDataMigrateApplication.class, args);
    }
}
