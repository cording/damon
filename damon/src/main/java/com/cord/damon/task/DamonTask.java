package com.cord.damon.task;


import com.cord.damon.bean.ToolUtil;
import com.cord.damon.config.ConfigInfoCache;
import org.apache.log4j.Logger;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lgm12 on 2017/4/22.
 */

public class DamonTask implements ApplicationListener<ApplicationStartedEvent>{

    Logger logger = Logger.getLogger(DamonTask.class);

    private static ScheduledExecutorService schedule = Executors.newScheduledThreadPool(30);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigInfoCache.CacheValue info = null;
        List<String> list = ToolUtil.getAllName();
        logger.info("程序用户名数:"+list);
        if(list!=null){
            for(String name : list){
                info = ConfigInfoCache.getInstance().get(name);
                if(info!=null){
                    schedule.scheduleAtFixedRate(new TaskDetail(info.getCommand(),info.getFilter(),info.getUser()),
                            0, 30, TimeUnit.SECONDS);
                }
            }
        }
    }

}
