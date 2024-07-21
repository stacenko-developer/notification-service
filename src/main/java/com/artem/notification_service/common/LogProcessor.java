package com.artem.notification_service.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogProcessor {

    @Before("execution(* com.artem.notification_service..*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        log.info("Начало выполнения метода: {} с аргументами: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.artem.notification_service..*(..))", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        log.info("Метод: {} выполнен. Возвращаемое значение: {}", joinPoint.getSignature(), result);
    }
}
