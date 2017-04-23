package com.cord.damon.service;

import com.cord.damon.config.DamonConfig;
import com.cord.damon.task.DamonTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lgm12 on 2017/1/18.
 */
@SpringBootApplication
public class DamonServiceApplication {
    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(DamonServiceApplication.class);
        springApplication.addListeners(new DamonTask());
        springApplication.run(args);

        System.out.println("已配置的程序名:"+ DamonConfig.config.Name());
    }
}

