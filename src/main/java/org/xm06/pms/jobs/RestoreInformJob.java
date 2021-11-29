package org.xm06.pms.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xm06.pms.Starter;
import org.xm06.pms.service.InformService;
import org.xm06.pms.websocket.WebSocketServer;


public class RestoreInformJob implements Job {
    Logger logger = LoggerFactory.getLogger(Starter.class);
    @Autowired
    InformService informService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("正在保存3日内的消息");
        informService.saveGroupInform(WebSocketServer.groupMessage);
        informService.saveNotReadInform(WebSocketServer.notReadMessage);
        WebSocketServer.clearStaticMsg();
        logger.info(WebSocketServer.groupMessage.toString());
        logger.info(WebSocketServer.notReadMessage.toString());
        logger.info("3日内的消息保存完毕");
    }
}
