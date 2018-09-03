package com.example.demo.common.config.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description
 * @Author 15407
 * @Date 2018/9/3 16:30
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected DataSourceRoutingKey determineCurrentLookupKey() {
        DataSourceRoutingKey dataSourceRoutingKey = DynamicDataSourceContextHolder.getDataSourceRoutingKey();
        if (null == dataSourceRoutingKey) {
            dataSourceRoutingKey = DataSourceRoutingKey.A;
        }
        logger.debug("current data source routing key is [{}]", dataSourceRoutingKey);
        return dataSourceRoutingKey;
    }
}
