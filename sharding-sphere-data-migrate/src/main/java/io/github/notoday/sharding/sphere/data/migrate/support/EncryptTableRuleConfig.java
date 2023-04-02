package io.github.notoday.sharding.sphere.data.migrate.support;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author no-today
 * @date 2023/04/01 22:40
 */
@Data
@Accessors(chain = true)
public class EncryptTableRuleConfig {

    private String tableName;
    private List<EncryptColumnRuleConfig> columns;
}
