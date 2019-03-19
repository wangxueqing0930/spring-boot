package com.example.demo.common.config;

import com.example.demo.common.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

/**
 * web mvc 配置
 * Created by wangxueqing on 2018/3/9.
 */
@Configuration
public class WebConfigAdapter extends WebMvcConfigurerAdapter {

    @Resource
    private LogInterceptor logInterceptor;

    /**
     * 若想拦截器中的bean注入成功，则应该使用这种方式获取拦截器
     * @return
     */
    @Bean
    LogInterceptor getLogInterceptor(){
        return new LogInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns用于设置拦截规则，excludePathPatterns用于排除拦截
        registry.addInterceptor(logInterceptor).addPathPatterns("/*").excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态访问资源
        registry.addResourceHandler("/");
        super.addResourceHandlers(registry);
    }
}
