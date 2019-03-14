package com.cjy.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestAysnc {


    @Async
    public void aysnc() throws InterruptedException {

        Thread.sleep(4000);
        System.out.println("我是异步调用-----");
    }
}
