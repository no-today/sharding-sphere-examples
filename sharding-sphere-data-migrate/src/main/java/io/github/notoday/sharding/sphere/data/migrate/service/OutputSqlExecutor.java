package io.github.notoday.sharding.sphere.data.migrate.service;

import io.github.notoday.sharding.sphere.data.migrate.config.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author no-today
 * @date 2023/04/01 21:20
 */
@Slf4j
@Component
public class OutputSqlExecutor implements SqlExecutor {

    private final ApplicationProperties properties;

    private final Map<String, List<String>> updateRows = new ConcurrentHashMap<>();
    private final Map<String, List<String>> alterRows = new ConcurrentHashMap<>();

    public OutputSqlExecutor(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void alter(String tableName, String sql) {
        this.alterRows.compute(tableName, (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            synchronized (v) {
                v.add(sql);
            }

            return v;
        });
    }

    @Override
    public void update(String tableName, String sql) {
        this.updateRows.compute(tableName, (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }

            synchronized (v) {
                v.add(sql);
            }

            return v;
        });
    }

    private List<String> getAlterRows(String tableName) {
        return alterRows.get(tableName);
    }

    private List<String> getUpdateRows(String tableName) {
        return updateRows.get(tableName);
    }

    @Override
    public void finished(String tableName) {
        String outputSqlScriptDir = properties.getOutputSqlScriptDir();
        log.info("[数据迁移-完成] table: {}, outputDir: {}", tableName, outputSqlScriptDir);

        try {
            Path outputDir = Path.of(outputSqlScriptDir);
            if (!Files.exists(outputDir)) {
                Files.createDirectory(outputDir);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputSqlScriptDir, String.format("%s.%s.sql", tableName, "alter")))) {
                for (String row : getAlterRows(tableName)) {
                    writer.write(row);
                    writer.newLine();
                }
            }

            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputSqlScriptDir, String.format("%s.%s.sql", tableName, "update")))) {
                for (String row : getUpdateRows(tableName)) {
                    writer.write(row);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
