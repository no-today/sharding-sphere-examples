### Quick Run The Demo


1. run mysql in docker
```shell
docker-compose -f mysql.yaml up -d
```

2. Run [ShardingSphereExamplesApplication](io.github.notoday.sharding.sphere.examples.ShardingSphereExamplesApplication)
3. Try requests [api.http](./sharding-sphere-spring-examples/api.http)
4. Run [ShardingSphereDataMigrateApplication](io.github.notoday.sharding.sphere.data.migrate.ShardingSphereDataMigrateApplication)
5. Run data migration scripts manually [sql-scripts](./sql-scripts)
6. Repeat step 3

### References

- [shardingsphere-jdbc-core-spring-boot-starter,When will version 5.3.0 be released?](https://github.com/apache/shardingsphere/issues/24258)
- [ShardingSphere 5.3 系列升级解读：Spring 配置升级指南](https://community.sphere-ex.com/t/topic/1284)