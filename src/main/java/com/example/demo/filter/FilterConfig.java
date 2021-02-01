package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<StatusFilter> statusBean(){
        FilterRegistrationBean<StatusFilter> statusBean = new FilterRegistrationBean<>();
        statusBean.setFilter(new StatusFilter());
        statusBean.addUrlPatterns("/api/locations/*");
        return statusBean;
    }
}
