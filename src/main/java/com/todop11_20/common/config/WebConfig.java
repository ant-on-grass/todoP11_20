package com.todop11_20.common.config;


import com.todop11_20.common.aop.AnnotationLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  @Bean
  public AnnotationLoggingAspect annotationLoggingAspect() {
    return new AnnotationLoggingAspect();
  }

}
