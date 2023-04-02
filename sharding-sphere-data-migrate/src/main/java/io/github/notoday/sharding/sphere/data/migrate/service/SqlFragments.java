package io.github.notoday.sharding.sphere.data.migrate.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author no-today
 * @date 2023/03/31 09:50
 */
public class SqlFragments {

    public static final String SELECT_DATABASE = "SELECT DATABASE()";

    /**
     * 传统分页查询
     * <p>
     * 性能将会随着 offset 值增大而降低，因为数据库需要扫描 offset 之前的所有行
     * <p>
     * 1. 主键列
     * 2. 明文列
     * 3. 表名
     * 4. offset
     * 5. pageSize
     */
    public static final String SELECT = "SELECT %s, %s FROM %s LIMIT %d, %d";

    /**
     * keyset 分页(seek)
     * <p>
     * 需要有一个唯一且有序的列。
     * SELECT id, column_1 FROM table_name WHERE id > last_id LIMIT page_size;
     * <p>
     * 1. 主键列
     * 2. 明文列
     * 3. 表名
     * 4. 主键条件
     * 5. 每批数量
     * <p>
     * SELECT id, email FROM user WHERE id > 0 LIMIT 200;
     * SELECT id, column_1, column_2 FROM user WHERE id > 0 LIMIT 200;
     */
    public static final String SELECT_SEEK_FIRST = "SELECT %s, %s FROM %s LIMIT %d";
    public static final String SELECT_SEEK = "SELECT %s, %s FROM %s WHERE %s > %s LIMIT %d";

    /**
     * 扩展表字段
     * <p>
     * 1. 表名
     * 2. 字段名
     * 3. 字符长度
     */
    public static final String EXTENSION_FIELD = "ALTER TABLE %s ADD %s VARCHAR(%d) NULL;";

    /**
     * 1. 表名
     * 2. 更新的密文列
     * 3. 主键条件
     * <p>
     * UPDATE user SET column_1 = ?, column_2 = ? WHERE id = ?;
     */
    public static final String UPDATE = "UPDATE %s SET %s WHERE %s;";

    public static String buildSelect(String tableName, String primaryKeyColumn, List<String> columns, Object lastId, int batchSize) {
        if (Objects.isNull(lastId)) {
            return String.format(SELECT_SEEK_FIRST, primaryKeyColumn, String.join(", ", columns), tableName, batchSize);
        } else {
            return String.format(SELECT_SEEK, primaryKeyColumn, String.join(", ", columns), tableName, primaryKeyColumn, getValue(lastId), batchSize);
        }
    }

    public static String buildUpdate(String tableName, Map<String, String> updateColumns, Map<String, Object> primaryKeysColumns) {
        String updateSql = updateColumns.entrySet().stream()
                .filter(e -> Objects.nonNull(e.getValue()))
                .map(e -> String.format("%s = %s", e.getKey(), getValue(e.getValue())))
                .collect(Collectors.joining(", "));

        String whereSql = primaryKeysColumns.entrySet().stream()
                .filter(e -> Objects.nonNull(e.getValue()))
                .map(e -> String.format("%s = %s", e.getKey(), getValue(e.getValue())))
                .collect(Collectors.joining(", "));

        return String.format(UPDATE, tableName, updateSql, whereSql);
    }

    public static String getValue(Object val) {
        if (val instanceof String) return "'" + val + "'";
        return val.toString();
    }
}
