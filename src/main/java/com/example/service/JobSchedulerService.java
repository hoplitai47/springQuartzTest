package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class JobSchedulerService {
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    public JobSchedulerService(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }
}