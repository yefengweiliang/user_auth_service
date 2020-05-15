package com.user.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 常见切面
 * @author wanghongshen
 * @date 2019-03-02
 */
@Aspect
@Configuration
@Slf4j
public class LogPrintAop {

    private Long startTime;
    private Long endTime;
    /**
     * 建立切点为@RequestMapping注释
     */
    @Pointcut("execution(* com.user.controller..*Controller.*(..))")
    public void printcut(){}

    /**
     * 在方法执行前打印参数
     * @param joinPoint 切入点获取参数和方法名
     */
    @Before("printcut()")
    public void logBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
       log.info("方法：{}, 请求的参数为:{}",joinPoint.getSignature().getName(), Arrays.toString(args));
       startTime = System.currentTimeMillis();
    }

    /**
     * 在方法执行之后答应结果
     * @param joinPoint 切入点获取参数和方法名
     * @param result 结果值
     */
    @AfterReturning(value = "printcut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        endTime = System.currentTimeMillis();
        log.info("方法:{}, 请求结果为:{}, 请求时间为:{}ms", joinPoint.getSignature().getName(), result, endTime-startTime);
    }

    /**
     * 在方法出异常打印异常
     * @param joinPoint 切入点获取参数和方法名
     * @param exception 异常信息
     */
    @AfterThrowing(value = "printcut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        endTime = System.currentTimeMillis();
        log.info("方法:{}异常, 异常信息为:{}, 请求时间为:{}ms", joinPoint.getSignature().getName(), exception, endTime-startTime);
    }

//    /**
//     * 环绕通知
//     * @param pjp 业务处理参数
//     */
//    @Around(value = "printcut()")
//    public void logAround(ProceedingJoinPoint pjp){
//
//        try {
//            log.info("方法：{}, 请求的参数为:{}",pjp.getSignature().getName(), Arrays.toString(pjp.getArgs()));
//            long startTime = System.currentTimeMillis();
//            Object proceed = pjp.proceed();
//            long afterTime = System.currentTimeMillis();
//            log.info("方法：{}, 请求结果为:{}, 请求时间为: {}", pjp.getSignature().getName(), proceed, afterTime-startTime);
//        } catch (Throwable throwable) {
//            log.info("方法：{}异常, 异常信息为: {} ", pjp.getSignature().getName(), throwable);
//
//        }
//
//    }

}
