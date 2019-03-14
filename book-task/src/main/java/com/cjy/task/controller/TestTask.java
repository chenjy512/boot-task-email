package com.cjy.task.controller;


import com.cjy.task.service.TestAysnc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTask {


    @Autowired
    TestAysnc testAysnc;


    @GetMapping
    public String hello(){
        try {
            testAysnc.aysnc();
        } catch (InterruptedException e) {

        }
        return "你好我是测试boot定时任务";
    }
}
