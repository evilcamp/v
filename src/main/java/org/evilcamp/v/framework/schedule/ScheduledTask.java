package org.evilcamp.v.framework.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled注解需要spring启动定时器注解
 */
@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);


    /**
     * 每三十秒执行一次
     */
    @Scheduled(cron="0/10 * * * * *")
    public  void doTask(){
        logger.info("QuartzTask start.");

        logger.info("QuartzTask end.");

    }
}
