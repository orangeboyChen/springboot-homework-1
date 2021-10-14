package com.example.springboottest.service.proxy.jdk;


import com.example.springboottest.service.impl.SalesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SalesServiceProxy implements InvocationHandler {

    private final Logger logger = LoggerFactory.getLogger(SalesServiceProxy.class);

    private Object object;

    public SalesServiceProxy() {
        object = new SalesServiceImpl();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Before sale called.");
        Object returnObject = method.invoke(object, args);
        logger.info("After sale called.");
        return returnObject;
    }
}
