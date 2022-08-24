package com.xiaotu.multidata.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages  = SlaveDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDatasourceConfig {
    // mysqldao扫描路径
    public static final String PACKAGE = "com.xiaotu.mysqldao";
    // mybatis mapper扫描路径
    public static final String MAPPER_LOCATION = "classpath:mapper/mysql/slaveStudentMapper.xml";

    @Primary
    @Bean(name = "slavedatasource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource mysqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slaveTransactionManager")
    @Primary
    public DataSourceTransactionManager slaveTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    @Primary
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slavedatasource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(SlaveDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
