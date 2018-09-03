package com.example.demo.common.config.jdbc;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author 15407
 * @Date 2018/9/3 15:21
 **/
@Configuration
@MapperScan(basePackages = "com.example.demo.dao", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {
    @Bean(name="dataSourceA")
    @ConfigurationProperties("spring.datasource.druid.A")
    @Primary
    public DataSource dataSourceA(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name="dataSourceB")
    @ConfigurationProperties("spring.datasource.druid.B")
    public DataSource dataSourceB(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("dataSourceA")DataSource dataSourceA,
                                                   @Qualifier("dataSourceB")DataSource dataSourceB){
        DynamicDataSource dynamicRoutingDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(16);
        dataSourceMap.put(DataSourceRoutingKey.A, dataSourceA);
        dataSourceMap.put(DataSourceRoutingKey.B, dataSourceB);
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        dynamicRoutingDataSource.afterPropertiesSet();
        return dynamicRoutingDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
        sqlSessionFactoryBean.afterPropertiesSet();
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
