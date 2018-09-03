package com.example.demo.common.annotation;


import com.example.demo.common.config.jdbc.DataSourceRoutingKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author: 范俊杰
 * @email:  fanjunjie@ehaier.com
 * @date:   2018/1/7
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceTarget {
    DataSourceRoutingKey value() default DataSourceRoutingKey.A;
}
