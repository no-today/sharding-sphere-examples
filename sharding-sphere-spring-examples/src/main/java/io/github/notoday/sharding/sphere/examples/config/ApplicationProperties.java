package io.github.notoday.sharding.sphere.examples.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author no-today
 * @date 2023/03/28 14:25
 */
@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {

    private Encrypt encrypt;

    @Data
    public static class Encrypt {
        private String aesKey;
    }
}
