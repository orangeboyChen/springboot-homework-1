package com.example.springboottest.controller;

import com.example.springboottest.annotation.Profile;
import com.example.springboottest.service.SalesService;
import com.example.springboottest.service.impl.SalesServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

@RestController
public class MainController {

    @Profile
    @GetMapping("/")
    public String get() {
        serviceMethod();

        com.example.springboottest.service.proxy.s.SalesServiceProxy p1 = new com.example.springboottest.service.proxy.s.SalesServiceProxy();
        p1.sell();

        com.example.springboottest.service.proxy.jdk.SalesServiceProxy handler = new com.example.springboottest.service.proxy.jdk.SalesServiceProxy();
        SalesService p2 = (SalesService) Proxy.newProxyInstance(SalesServiceImpl.class.getClassLoader(), SalesServiceImpl.class.getInterfaces(), handler);
        p2.sell();

        SalesService p3 = (SalesService) new com.example.springboottest.service.proxy.cglib.SalesServiceProxy().getInstance();
        p3.sell();
        p3.buy();

        return "{code: 200, message: \"successfully visited.\"}";
    }


    public void serviceMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
