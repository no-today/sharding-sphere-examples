package io.github.notoday.sharding.sphere.data.migrate.service;

import io.github.notoday.sharding.sphere.data.migrate.config.ApplicationProperties;
import io.github.notoday.sharding.sphere.data.migrate.support.EncryptColumnRuleConfig;
import io.github.notoday.sharding.sphere.data.migrate.support.EncryptTableRuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.encrypt.api.encrypt.like.LikeEncryptAlgorithm;
import org.apache.shardingsphere.encrypt.api.encrypt.standard.StandardEncryptAlgorithm;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.notoday.sharding.sphere.data.migrate.service.SqlFragments.*;

/**
 * 只读，生成迁移SQL脚本，变更执行与否由 SqlExecutor 决定
 *
 * @author no-today
 * @date 2023/03/22 10:56
 */
@Slf4j
@Service
public class DataMigrationService implements DataMigration {

    private final ApplicationProperties properties;
    private final JdbcTemplate jdbcTemplate;
    private final SqlExecutor sqlExecutor;
    private final String databaseName;

    public DataMigrationService(ApplicationProperties properties, JdbcTemplate jdbcTemplate, SqlExecutor sqlExecutor) {
        this.properties = properties;
        this.jdbcTemplate = jdbcTemplate;
        this.sqlExecutor = sqlExecutor;
        this.databaseName = jdbcTemplate.queryForObject(SELECT_DATABASE, String.class);
    }

    @Override
    public void process(EncryptTableRuleConfig config) {
        String tableName = config.getTableName();

        List<String> primaryKeyColumns = getPrimaryKeyColumns(tableName);
        if (isNotSupportedForMultiplePrimaryKeys(tableName, primaryKeyColumns.size())) return;

        int fieldLength = properties.getEncryptedFieldLength();
        alterTableFields(fieldLength, config);

        String primaryKeyColumn = primaryKeyColumns.get(0);
        int batchSize = properties.getBatchSize();
        Object lastId = null;

        log.info("[数据迁移-开始] table: {}, fieldLength: {}, batchSize: {}", tableName, fieldLength, batchSize);

        do {
            log.debug("[数据迁移-处理] table: {}, lastId: {}", tableName, lastId);

            List<String> columns = config.getColumns().stream().map(EncryptColumnRuleConfig::getColumn).collect(Collectors.toList());
            String selectSql = buildSelect(tableName, primaryKeyColumn, columns, lastId, batchSize);
            SqlRowSet resultSet = jdbcTemplate.queryForRowSet(selectSql);

            lastId = processRows(resultSet, primaryKeyColumn, config);
        } while (!Objects.isNull(lastId));

        sqlExecutor.finished(tableName);
    }

    private boolean isNotSupportedForMultiplePrimaryKeys(String tableName, int primaryKeyColumnsSize) {
        if (primaryKeyColumnsSize > 1) {
            log.warn("[数据迁移] 当前策略不支持迁移多主键表: {}", tableName);
            return true;
        }
        return false;
    }

    private Object processRows(SqlRowSet resultSet, String primaryKeyColumn, EncryptTableRuleConfig config) {
        Object lastId = null;

        while (resultSet.next()) {
            lastId = processRow(resultSet, primaryKeyColumn, config);
        }

        return lastId;
    }

    private Object processRow(SqlRowSet resultSet, String primaryKeyColumn, EncryptTableRuleConfig config) {

        Map<String, Object> primaryKeysColumns = Stream.of(primaryKeyColumn).collect(Collectors.toMap(e -> e, e -> Optional.ofNullable(resultSet.getObject(e)).orElseThrow(), (a, b) -> a, LinkedHashMap::new));
        Map<String, String> updateColumns = buildUpdateColumns(resultSet::getObject, config.getColumns());

        String updateSql = buildUpdate(config.getTableName(), updateColumns, primaryKeysColumns);
        sqlExecutor.update(config.getTableName(), updateSql);

        return primaryKeysColumns.get(primaryKeyColumn);
    }

    private Map<String, String> buildUpdateColumns(Function<String, Object> getColumnValue, List<EncryptColumnRuleConfig> columns) {
        Map<String, String> cipherColumns = new LinkedHashMap<>();

        columns.forEach(e -> {
            Object columnValue = getColumnValue.apply(e.getColumn());

            String cipherColumn = e.getCipherColumn();
            String assistedQueryColumn = e.getAssistedQueryColumn();
            String likeQueryColumn = e.getLikeQueryColumn();

            StandardEncryptAlgorithm<Object, String> cipherEncryptor = e.getCipherEncryptor();
            StandardEncryptAlgorithm<Object, String> assistedQueryEncryptor = e.getAssistedQueryEncryptor();
            LikeEncryptAlgorithm<Object, String> likeQueryEncryptor = e.getLikeQueryEncryptor();

            if (StringUtils.isNotBlank(cipherColumn)) {
                cipherColumns.put(cipherColumn, cipherEncryptor.encrypt(columnValue, null));
            }
            if (StringUtils.isNotBlank(assistedQueryColumn)) {
                cipherColumns.put(assistedQueryColumn, assistedQueryEncryptor.encrypt(columnValue, null));
            }
            if (StringUtils.isNotBlank(likeQueryColumn)) {
                cipherColumns.put(likeQueryColumn, likeQueryEncryptor.encrypt(columnValue, null));
            }
        });

        return cipherColumns;
    }

    private List<String> getPrimaryKeyColumns(String tableName) {
        return jdbcTemplate.execute((ConnectionCallback<List<String>>) connection -> {
            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet resultSet = metaData.getPrimaryKeys(null, null, tableName);
            List<String> primaryKeys = new ArrayList<>();
            while (resultSet.next()) {
                if (databaseName.equalsIgnoreCase(resultSet.getString(1))) {
                    primaryKeys.add(resultSet.getString("COLUMN_NAME"));
                }
            }
            return primaryKeys;
        });
    }

    private void alterTableFields(int fieldLength, EncryptTableRuleConfig config) {
        String tableName = config.getTableName();
        config.getColumns().forEach(e -> {
            String cipherColumn = e.getCipherColumn();
            String assistedQueryColumn = e.getAssistedQueryColumn();
            String likeQueryColumn = e.getLikeQueryColumn();

            if (StringUtils.isNotBlank(cipherColumn)) {
                sqlExecutor.alter(tableName, String.format(EXTENSION_FIELD, tableName, cipherColumn, fieldLength));
            }
            if (StringUtils.isNotBlank(assistedQueryColumn)) {
                sqlExecutor.alter(tableName, String.format(EXTENSION_FIELD, tableName, assistedQueryColumn, fieldLength));
            }
            if (StringUtils.isNotBlank(likeQueryColumn)) {
                sqlExecutor.alter(tableName, String.format(EXTENSION_FIELD, tableName, likeQueryColumn, fieldLength));
            }
        });
    }
}
