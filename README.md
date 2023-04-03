### Quick Run The Demo


1. Run mysql in docker

```shell
docker-compose -f mysql.yaml up -d
```

2. Init database, run sql scripts: 
   1. [user.sql](./sql/user.sql)
   2. [user_extend.sql](./sql/user_extend.sql)
3. Run [ShardingSphereDataMigrateApplication](./sharding-sphere-data-migrate/src/main/java/io/github/notoday/sharding/sphere/data/migrate/ShardingSphereDataMigrateApplication.java)
   1. run it directly to generate a migration script for the data source `ds1`
   2. change [application.yaml](sharding-sphere-data-migrate/src/main/resources/application.yaml), `sharding-config: sharding-ds2.yaml`, and data source url to: `ds2`, **run again**
4. Run data migration sql scripts [sql-scripts](./sql-scripts)
5. Run [ShardingSphereExamplesApplication](./sharding-sphere-spring-examples/src/main/java/io/github/notoday/sharding/sphere/examples/ShardingSphereExamplesApplication.java)
6. Try requests [api.http](./sharding-sphere-spring-examples/api.http)

### TODO

#### When cross-library join, the whole table will be queried, and then join in memory

1. KernelProcessor#generateExecutionContext
2. FilterableTableScanExecutor#execute
   1. watches: `queryResult`, you will find that the whole table is queried, not by id.
3. ExecutorEngine#execute

### References

- [shardingsphere-jdbc-core-spring-boot-starter,When will version 5.3.0 be released?](https://github.com/apache/shardingsphere/issues/24258)
- [ShardingSphere 5.3 系列升级解读：Spring 配置升级指南](https://community.sphere-ex.com/t/topic/1284)