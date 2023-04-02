package io.github.notoday.sharding.sphere.examples;

import io.github.notoday.sharding.sphere.examples.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class ShardingSphereExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereExamplesApplication.class, args);
    }
}
