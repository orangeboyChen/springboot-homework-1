package com.example.springboottest.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ProfileAspect {
    private final Logger logger = LoggerFactory.getLogger(ProfileAspect.class);

    @Around("@annotation(com.example.springboottest.annotation.Profile)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();

        Signature signature = pjp.getSignature();

        Object res =  pjp.proceed();
        time = System.currentTimeMillis() - time;

        logger.info("{}() runtime: {}ms", signature.getName(), time);
        return res;
    }


}
