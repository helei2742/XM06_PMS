package org.xm06.pms.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.xm06.pms.Starter;
import org.xm06.pms.service.ProjectService;


public class DeleteProjectCheckCodeJob implements Job {
    Logger logger = LoggerFactory.getLogger(Starter.class);


    @Autowired
    ProjectService projectService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("正在删除今日过期验证码");
        int count = projectService.deleteCheckCode();
        logger.info("删除今日过期验证码完毕,共删除 "+count+" 条");
    }
}
