package com.example.demo.common.aop;

import com.example.demo.common.annotation.DataSourceTarget;
import com.example.demo.common.config.jdbc.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DaoAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution( * com.example.demo.dao..*.*(..))")
    public void daoAspect() {

    }

    @Before("daoAspect()")
    public void switchDataSource(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DataSourceTarget dataSourceTarget = AnnotationUtils.findAnnotation(method, DataSourceTarget.class);
        if (dataSourceTarget != null) {
            logger.debug("set data source {}", dataSourceTarget.value());
            DynamicDataSourceContextHolder.setDataSourceRoutingKey(dataSourceTarget.value());
        }
    }

    @After("daoAspect()")
    public void restoreDataSource(JoinPoint joinPoint) {
        logger.debug("clear data source routingKey...");
        DynamicDataSourceContextHolder.clearDataSourceRoutingKey();
    }
}
