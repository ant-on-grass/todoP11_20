package com.todop11_20.common.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AnnotationLoggingAspect {

  @Pointcut("@annotation(com.todop11_20.common.aop.LoggingAop)")
  private void loggingAopAnnotation() {}

  @Around("loggingAopAnnotation()")
  public Object annotaionAopTest(ProceedingJoinPoint joinPoint) {
    long startTime = System.currentTimeMillis();

    try {
      Object result = joinPoint.proceed();
      return result;
    } catch (Throwable e) {
      throw new RuntimeException("횡단 관심사 실행 중 오류 발생 ");
    } finally {
      long endTime = System.currentTimeMillis();
      long excutionTime = endTime - startTime;
      log.info("::: ExcutionTime : {}ms",excutionTime);
    }

  }



}
