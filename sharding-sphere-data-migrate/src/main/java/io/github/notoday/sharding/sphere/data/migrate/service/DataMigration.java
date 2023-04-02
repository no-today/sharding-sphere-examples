package io.github.notoday.sharding.sphere.data.migrate.service;

import io.github.notoday.sharding.sphere.data.migrate.support.EncryptTableRuleConfig;

/**
 * 一个字段一个字段的进行迁移
 * <p>
 * 优点是改动点可控，SQL 语句清晰
 * 缺点是当同一个表有多个需要迁移的字段时效率低下
 *
 * @author no-today
 * @date 2023/03/31 09:16
 */
public interface DataMigration {

    void process(EncryptTableRuleConfig config);
}
