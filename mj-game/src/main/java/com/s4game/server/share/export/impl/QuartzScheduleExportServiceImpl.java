package com.s4game.server.share.export.impl;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.springframework.stereotype.Component;

import com.s4game.core.schedule.QuartzScheduleExecutor;
import com.s4game.server.share.export.IQuartzScheduleExportService;

/**
 * @author zeusgooogle@gmail.com
 * @date 2014年10月21日 下午10:04:03
 */

@Component
public class QuartzScheduleExportServiceImpl implements IQuartzScheduleExportService {
	
	@Resource
	private QuartzScheduleExecutor quartzScheduleExecutor;

	public QuartzScheduleExportServiceImpl() {
	}

	public String schedule(Object target, String method, Object[] args, String cronExpression) {
		String jobName = "" + System.nanoTime();
		return this.quartzScheduleExecutor.schedule(jobName, target, method, args, cronExpression);
	}

	public String schedule(Object target, String method, Object[] args, CronTrigger cronTrigger) {
	    String jobName = "" + System.nanoTime();
		return this.quartzScheduleExecutor.schedule(jobName, target, method, args, cronTrigger);
	}

}
