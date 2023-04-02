package io.github.notoday.sharding.sphere.examples.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author no-today
 * @date 2023/03/28 14:29
 */
@Configuration
@MapperScan("io.github.notoday.sharding.sphere.examples.repository")
public class MybatisConfiguration {
}
