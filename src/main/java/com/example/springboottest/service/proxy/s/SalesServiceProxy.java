package com.example.springboottest.service.proxy.s;

import com.example.springboottest.service.SalesService;
import com.example.springboottest.service.impl.SalesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesServiceProxy implements SalesService {
    private final Logger logger = LoggerFactory.getLogger(SalesServiceProxy.class);

    private final SalesServiceImpl salesService = new SalesServiceImpl();
    @Override
    public void sell() {
        logger.info("Before sell called.");
        salesService.sell();
        logger.info("After sell called.");
    }

    @Override
    public void buy() {
        logger.info("Before buy called.");
        salesService.buy();
        logger.info("After buy called.");
    }
}
