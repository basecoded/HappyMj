package com.s4game.core.schedule;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * @author zeusgooogle
 * @date 2014-9-30 下午04:43:47
 */
public class QuartzScheduleExecutor {

    private Scheduler quartzScheduler;

    private static final String DEFAULT_GROUP = "group1";

    public QuartzScheduleExecutor() {
    }

    public void setQuartzScheduler(Scheduler quartzScheduler) {
        this.quartzScheduler = quartzScheduler;
    }

    private JobDetail createMethodInvokeJobDetail(String jobName, Object target, String method, Object[] args) {
        JobDetail jobDetail = null;
        try {
            if (null == jobName) {
                jobName = "" + System.nanoTime();
            }

            MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
            methodInvokingJobDetailFactoryBean.setName(jobName);

            methodInvokingJobDetailFactoryBean.setTargetObject(target);
            methodInvokingJobDetailFactoryBean.setTargetMethod(method);
            methodInvokingJobDetailFactoryBean.setArguments(args);
            methodInvokingJobDetailFactoryBean.setConcurrent(false);
            methodInvokingJobDetailFactoryBean.setGroup(DEFAULT_GROUP);

            methodInvokingJobDetailFactoryBean.afterPropertiesSet();

            jobDetail = methodInvokingJobDetailFactoryBean.getObject();

        } catch (Exception e) {
            throw new RuntimeException("createMethodInvokeJobDetail error", e);
        }
        return jobDetail;
    }

    public String schedule(String jobName, Object target, String method, Object[] args, String cronExpression) {
        JobDetail jobDetail = createMethodInvokeJobDetail(jobName, target, method, args);

        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName(jobDetail.getKey().getName());
        cronTriggerFactoryBean.setGroup(DEFAULT_GROUP);
        cronTriggerFactoryBean.setCronExpression(cronExpression);

        try {
            this.quartzScheduler.scheduleJob(jobDetail, cronTriggerFactoryBean.getObject());
        } catch (Exception e) {
            throw new RuntimeException("schedule error method:" + method, e);
        }

        return jobDetail.getKey().getName();
    }

    public String schedule(String jobName, Object target, String method, Object[] args, CronTrigger cronTrigger) {
        JobDetail jobDetail = createMethodInvokeJobDetail(jobName, target, method, args);

        try {
            this.quartzScheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            throw new RuntimeException("schedule error method:" + method, e);
        }
        return jobDetail.getKey().getName();
    }

    public void cancelSchedule(String name, String group) {
        try {
            this.quartzScheduler.unscheduleJob(new TriggerKey(name, group));
        } catch (SchedulerException e) {
            throw new RuntimeException("cancelSchedule error quartz job: " + name, e);
        }
    }

}
