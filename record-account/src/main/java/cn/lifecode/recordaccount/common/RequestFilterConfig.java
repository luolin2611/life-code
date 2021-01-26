package cn.lifecode.recordaccount.common;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 过滤器配置
 *
 * @author luolin
 * @date 2021-01-24 16:38:11
 */
@Configuration
public class RequestFilterConfig {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(requestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("requestFilter");
        return registration;
    }

    @Bean(name = "requestFilter")
    public Filter requestFilter() {
        return new RequestFilter();
    }
}
