package com.cord.damon.task;

import com.cord.damon.bean.ToolUtil;
import org.apache.log4j.Logger;


/**
 * Created by lgm12 on 2017/4/22.
 *
 * 监控任务具体内容
 */
public class TaskDetail implements Runnable {

    Logger logger = Logger.getLogger(TaskDetail.class);

    private static final String checkCmd = ToolUtil.shellPath + "check.sh %s %s";

    private String command;

    private String filter;

    private String user;

    public TaskDetail(String command, String filter, String user){
        this.command=command;
        this.filter=filter;
        this.user=user;
    }

    @Override
    public void run() {
        logger.info("执行守护进程任务.");
        if(this.user.isEmpty()||this.filter.isEmpty()||this.command.isEmpty()){
            return;
        }

        try{
            //查看进程运行状态
            int ret = ToolUtil.invokeShell(String.format(checkCmd, this.user,this.filter));

            if(ret<1){
                ToolUtil.invokeShell(this.command);
            }

        }catch(Exception e){
            logger.error(e);
        }

    }
}
