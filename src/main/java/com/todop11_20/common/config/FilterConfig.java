package com.todop11_20.common.config;

import com.todop11_20.common.config.jwt.JwtFilter;
import com.todop11_20.common.config.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
  private final JwtUtil jwtUtil;

  @Bean
  public FilterRegistrationBean<JwtFilter> jwtFilter() {
    FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new JwtFilter(jwtUtil));
    registrationBean.addUrlPatterns("/*");

    return registrationBean;
  }
}
