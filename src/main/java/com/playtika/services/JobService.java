package com.playtika.services;

import java.util.Date;
import java.util.UUID;

import javax.xml.crypto.Data;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.playtika.job.SimpleJob2;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Maksym Samsonov.
 * @since 11/12/17.
 */
@Service
public class JobService {

    private final String groupName = "normal-group";

    private final Scheduler scheduler;

    @Autowired
    public JobService(SchedulerFactoryBean schedulerFactory) {
        this.scheduler = schedulerFactory.getScheduler();
    }

    public String addNewJob(long firstStart, int interval, String identity, int repeatCount) throws SchedulerException {
        String id = UUID.randomUUID().toString();

        JobDetail job =
            newJob(SimpleJob2.class)
                .withIdentity(identity)
                .requestRecovery(true)
                .build();

        Trigger trigger =
            newTrigger()
                .withIdentity(id, groupName)
                .startAt(new Date(firstStart*1000L))
                .withSchedule(
                    simpleSchedule().withIntervalInSeconds(interval).withRepeatCount(repeatCount)
                )
                .build();
        scheduler.scheduleJob(job, trigger);
        return id;
    }

    public boolean deleteJob(String id) throws SchedulerException {
        return scheduler.deleteJob(JobKey.jobKey(id));
    }

}