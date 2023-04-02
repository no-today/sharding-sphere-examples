### Quick Run The Demo


1. run mysql in docker
```shell
docker-compose -f mysql.yaml up -d
```

2. init database

```sql
CREATE DATABASE ds1;
USE ds1;

CREATE TABLE user
(
    id                 BIGINT       NOT NULL AUTO_INCREMENT,
    last_name          VARCHAR(16)  NOT NULL,
    first_name         VARCHAR(16)  NOT NULL,
    username           VARCHAR(32)  NOT NULL,
    password           VARCHAR(128) NOT NULL,
    email              VARCHAR(32)  NOT NULL,
    created_date       DATETIME     NOT NULL,
    last_modified_date DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX user_username ON user(username);

INSERT INTO user (username, password, last_name, first_name, email, created_date, last_modified_date) VALUES
    ('user1', 'password1', 'Smith', 'John', 'john.smith@example.com', '2023-03-28 10:00:00', '2023-03-28 10:00:00'),
    ('user2', 'password2', 'Doe', 'Jane', 'jane.doe@example.com', '2023-03-28 10:15:00', '2023-03-28 10:15:00'),
    ('user3', 'password3', 'Johnson', 'Michael', 'michael.johnson@example.com', '2023-03-28 10:30:00', '2023-03-28 10:30:00'),
    ('user4', 'password4', 'Brown', 'Emily', 'emily.brown@example.com', '2023-03-28 10:45:00', '2023-03-28 10:45:00'),
    ('user5', 'password5', 'Lee', 'Daniel', 'daniel.lee@example.com', '2023-03-28 11:00:00', '2023-03-28 11:00:00'),
    ('user6', 'password6', 'Patel', 'Sarah', 'sarah.patel@example.com', '2023-03-28 11:15:00', '2023-03-28 11:15:00'),
    ('user7', 'password7', 'Rodriguez', 'William', 'william.rodriguez@example.com', '2023-03-28 11:30:00', '2023-03-28 11:30:00'),
    ('user8', 'password8', 'Jackson', 'Jessica', 'jessica.jackson@example.com', '2023-03-28 11:45:00', '2023-03-28 11:45:00'),
    ('user9', 'password9', 'Garcia', 'David', 'david.garcia@example.com', '2023-03-28 12:00:00', '2023-03-28 12:00:00'),
    ('user10', 'password10', 'Lopez', 'Olivia', 'olivia.lopez@example.com', '2023-03-28 12:15:00', '2023-03-28 12:15:00');


-- 新增加密列
alter table user add email_encrypted varchar(255) null;
alter table user add email_query_helper varchar(255) null;
alter table user add email_like_query varchar(255) null;
```

```sql
CREATE DATABASE ds2;
USE ds2;

CREATE TABLE user_extend
(
    user_id            BIGINT   NOT NULL,
    ext_field1         VARCHAR(255) NULL,
    ext_field2         VARCHAR(255) NULL,
    ext_field3         VARCHAR(255) NULL,
    last_modified_date DATETIME NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO user_extend (user_id, ext_field1, ext_field2, ext_field3, last_modified_date) VALUES
    (1, 'A1B2C3', 'D1E2F3', 'G1H2I3', '2023-03-21 12:45:32'),
    (2, 'A2B3C4', 'D2E3F4', 'G2H3I4', '2023-03-22 11:23:45'),
    (3, 'A3B4C5', 'D3E4F5', 'G3H4I5', '2023-03-20 14:56:12'),
    (4, 'A4B5C6', 'D4E5F6', 'G4H5I6', '2023-03-19 09:27:38'),
    (5, 'A5B6C7', 'D5E6F7', 'G5H6I7', '2023-03-21 15:48:23'),
    (6, 'A6B7C8', 'D6E7F8', 'G6H7I8', '2023-03-22 16:59:08'),
    (7, 'A7B8C9', 'D7E8F9', 'G7H8I9', '2023-03-18 13:21:45'),
    (8, 'A8B9C0', 'D8E9F0', 'G8H9I0', '2023-03-19 18:30:52'),
    (9, 'A9B0C1', 'D9E0F1', 'G9H0I1', '2023-03-20 17:42:37'),
    (10, 'A0B1C2', 'D0E1F2', 'G0H1I2', '2023-03-23 11:34:56');

-- 新增加密列
ALTER TABLE user_extend ADD user_id_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD user_id_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_like_query VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field2_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field1_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field2_like_query VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field3_encrypted VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field3_query_helper VARCHAR(255) NULL;
ALTER TABLE user_extend ADD ext_field3_like_query VARCHAR(255) NULL;
```

3. Run [ShardingSphereExamplesApplication](io.github.notoday.sharding.sphere.examples.ShardingSphereExamplesApplication)
4. Try requests [api.http](./sharding-sphere-spring-examples/api.http), `old data not processed`
5. Run [ShardingSphereDataMigrateApplication](io.github.notoday.sharding.sphere.data.migrate.ShardingSphereDataMigrateApplication)
6. Run data migration scripts manually [sql-scripts](./sql-scripts)
7. Repeat step 4, `old data processed`

### References

- [shardingsphere-jdbc-core-spring-boot-starter,When will version 5.3.0 be released?](https://github.com/apache/shardingsphere/issues/24258)
- [ShardingSphere 5.3 系列升级解读：Spring 配置升级指南](https://community.sphere-ex.com/t/topic/1284)