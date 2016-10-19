package com.s4game.server.share.export;

import org.quartz.CronTrigger;

/**
 * @author zeusgooogle@gmail.com
 * @date 2014年10月21日 下午10:00:41
 */
public interface IQuartzScheduleExportService {

	public String schedule(Object target, String method, Object[] args, String cronExpression);

	public String schedule(Object target, String method, Object[] args, CronTrigger cronTrigger);

}
