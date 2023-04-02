## 为线上成熟业务的敏感字段进行加密处理

### 扫描敏感字段

```sql
-- 设置要查找的数据库名
SET @database_name = 'database';
-- 设置要查找的表（使用'|'分隔）
SET @table_name_pattern = 'table_1|table_2';
-- 设置要查找的字段名（使用'|'分隔）
SET @field_name_pattern = 'field_1|field_2';

-- 查询匹配的表和字段
SELECT
  TABLE_SCHEMA,
  TABLE_NAME,
  GROUP_CONCAT(COLUMN_NAME) COLUMNS
FROM
  INFORMATION_SCHEMA.COLUMNS
WHERE
  TABLE_SCHEMA = @database_name
  AND TABLE_NAME REGEXP @table_name_pattern
  AND COLUMN_NAME REGEXP @field_name_pattern
GROUP BY TABLE_SCHEMA, TABLE_NAME;
```

### 扩展加密列

### 存量数据