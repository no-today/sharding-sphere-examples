package io.github.notoday.sharding.sphere.data.migrate.service;

/**
 * @author no-today
 * @date 2023/04/01 21:05
 */
public interface SqlExecutor {

    void alter(String tableName, String sql);

    void update(String tableName, String sql);

    void finished(String tableName);
}
