package com.playtika.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.playtika.services.QuartzService;

/**
 * @author Maksym Samsonov.
 * @since 11/12/17.
 */
public class SimpleJob implements Job{

    @Autowired
    QuartzService quartzService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        quartzService.processData();
    }
}
