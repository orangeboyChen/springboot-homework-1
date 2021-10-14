package com.example.springboottest.service.proxy.cglib;

import com.example.springboottest.service.impl.SalesServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.reflect.Method;

@Setter
@Getter
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SalesServiceProxy {

    public static Object getObject() {
        return object;
    }

    public static void setObject(Object object) {
        SalesServiceProxy.object = object;
    }

    //为什么用静态呢？不用静态会报一个tostring的错误，目前暂无查询到解决方法，估计是springboot内自动给类加代理
    private static Object object;

    private final static Logger logger = LoggerFactory.getLogger(SalesServiceProxy.class);

    public Object getInstance() {
        SalesServiceProxy instance = new SalesServiceProxy();
        instance.setObject(new SalesServiceImpl());

        Callback[] callbacks = new Callback[] {
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    logger.info("Before sell called.");
                    Object result = method.invoke(object, objects);
                    logger.info("After sell called.");
                    return result;
                }
                ,
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    logger.info("Before buy called.");
                    Object result = method.invoke(object, objects);
                    logger.info("After buy called.");
                    return result;
                }
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(instance.getObject().getClass());
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(method -> "sell".equals(method.getName()) ? 0 : 1);

        return enhancer.create();
    }
}
