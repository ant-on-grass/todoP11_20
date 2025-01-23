package com.todop11_20.common.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AnnotationLoggingAspect {

  private static final Logger logger= LoggerFactory.getLogger(AnnotationLoggingAspect.class);

  @Pointcut("@annotation(com.todop11_20.common.aop.LoggingAop)")
  private void loggingAopAnnotation() {}

  @Around("loggingAopAnnotation()")
  public Object annotaionAopTest(ProceedingJoinPoint joinPoint) {
    long startTime = System.currentTimeMillis();

    try {
      Object result = joinPoint.proceed();
      return result;
    }  catch (Throwable e) {
      logger.error("::: aop error :::");
      throw new RuntimeException("test");
    } finally {
      long endTime = System.currentTimeMillis();
      long excutionTime = endTime - startTime;
      logger.info("::: ExcutionTime : {}ms",excutionTime);
      return null;
    }

  }



}
