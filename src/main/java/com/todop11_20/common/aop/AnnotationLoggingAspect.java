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

  private static final Logger logger= LoggerFactory.getLogger(AnnotationLoggingAspect.class); // 의존성 주입 필요!

  @Pointcut("@annotation(com.todop11_20.common.aop.LoggingAop)")
  private void loggingAopAnnotation() {}

  @Around("loggingAopAnnotation()")
  public Object annotaionAopTest(ProceedingJoinPoint joinPoint) {
    logger.error("::: todo 조회 서비스 시작 :::");  // 로깅 config 에서 logger를 씀으로써 특정 클래스에 로그를 남길 때, log 대신 logger 를 쓴다
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
      logger.error("::: todo 조회 서비스 종료 :::");
      logger.info("::: todo 조회 서비스 ExcutionTime : {}ms :::",excutionTime);
      return null;
    }

  }



}
