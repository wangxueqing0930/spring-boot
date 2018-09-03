package com.example.demo.common.config.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author 15407
 * @Date 2018/9/3 16:33
 **/
public class DynamicDataSourceContextHolder {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static final ThreadLocal<DataSourceRoutingKey> CONTEXT_HOLDER = new ThreadLocal<DataSourceRoutingKey>();

    public static List<Object> dataSourceRoutingKeys = new ArrayList<Object>();

    public static void setDataSourceRoutingKey(DataSourceRoutingKey key) {
        CONTEXT_HOLDER.set(key);
    }

    public static DataSourceRoutingKey getDataSourceRoutingKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceRoutingKey() {
        CONTEXT_HOLDER.remove();
    }

    public static void useADataSource() {
        CONTEXT_HOLDER.set(DataSourceRoutingKey.A);
    }

    public static void useBDataSource() {
        CONTEXT_HOLDER.set(DataSourceRoutingKey.B);
    }
}
