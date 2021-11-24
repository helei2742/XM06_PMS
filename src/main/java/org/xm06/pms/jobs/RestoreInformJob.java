package org.xm06.pms.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xm06.pms.service.InformService;
import org.xm06.pms.websocket.WebSocketServer;


public class RestoreInformJob implements Job {
    private Logger logger = LoggerFactory.getLogger(RestoreInformJob.class);

    @Autowired
    InformService informService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始保存");
        informService.saveGroupInform(WebSocketServer.groupMessage);
        informService.saveNotReadInform(WebSocketServer.notReadMessage);
        WebSocketServer.clearStaticMsg();
        System.out.println("保存完毕");
    }
}
