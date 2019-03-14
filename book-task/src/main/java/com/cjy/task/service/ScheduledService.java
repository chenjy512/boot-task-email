package com.cjy.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron="0/4 * * * * *")
    public void hello(){
        System.out.println("我是boot的定时任务触发-------");
    }
}
