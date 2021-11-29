package org.xm06.pms.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xm06.pms.Starter;
import org.xm06.pms.service.SystemRecordService;


public class SystemRecordSaveJob implements Job {
    Logger logger = LoggerFactory.getLogger(Starter.class);
    @Autowired
    SystemRecordService systemRecordService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("正在保存当日系统使用情况");
        systemRecordService.restoreSystemRecord();
        logger.info("当日系统使用情况保存完毕");
    }
}
