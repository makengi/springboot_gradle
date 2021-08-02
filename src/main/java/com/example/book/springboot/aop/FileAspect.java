package com.example.book.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class FileAspect {

    //Advice Definition (used Around)
    @Around("execution(* com.example.book.springboot.domain.file.service.FileDownloadService.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable{
        long beginTime = System.currentTimeMillis();
        Object retVal =  pjp.proceed();
        log.info("@ diff: {}", System.currentTimeMillis() - beginTime);
        return retVal;
    }
}
