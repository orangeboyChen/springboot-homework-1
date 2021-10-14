package com.example.springboottest.service.impl;

import com.example.springboottest.service.SalesService;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@ToString
public class SalesServiceImpl implements SalesService {

    private final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);

    private final String id = UUID.randomUUID().toString().replace("-", "");

    @Override
    public void sell() {
        logger.info("sale called.");
    }

    @Override
    public void buy() {
        logger.info("buy called.");
    }
}
