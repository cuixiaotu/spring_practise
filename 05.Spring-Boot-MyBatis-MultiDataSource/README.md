# 05.Spring-Boot-MyBatis-MultiDataSource

Spring中配置MyBatis SqlSessionFactory

```xml
<!-- mybatis 的SqlSessionFactory -->
<bean id="SqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" scope="prototype">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
</bean>
```

所以实际上在Spring Boot中配置MyBatis多数据源的关键在于创建SqlSessionFactory的时候为其分配不同的数据源

## 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.13</version>
</dependency>
```



## 多数据源配置

在Spring Boot配置文件application.yml中配置多数据源和Spring Boot JdbcTemplate配置Druid多数据源一致。

然后根据application.yml创建两个数据源配置类MysqlDatasource1Config和MysqlDatasource2Config：







