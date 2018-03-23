package com.example.demo.common.config;

import com.example.demo.common.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2018/3/9.
 */
@Configuration
public class WebConfigAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns用于设置拦截规则，excludePathPatterns用于排除拦截
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/*").excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态访问资源
        registry.addResourceHandler("/");
        super.addResourceHandlers(registry);
    }
}
