package org.example.calendarapplication.common.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterFilterRegistrationBean.setFilter(new LoginFilter());  // Filter를 등록함
        filterFilterRegistrationBean.addUrlPatterns("/*");          // 전체 URL에 Filter를 적용함

        return filterFilterRegistrationBean;
    }
}
