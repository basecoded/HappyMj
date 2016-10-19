package com.s4game.server.share.export;

import java.util.concurrent.TimeUnit;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年9月8日 上午11:47:25
 *
 */

public interface IStageScheduleExportService {

    /**
     * 场景调度
     * 
     * @param moduleName
     * @param taskId
     * @param command
     * @param data
     * @param delay
     * @param timeUnit
     */
    public void schedule(String moduleName, String taskId, String command, Object data, long delay, TimeUnit timeUnit);
    
    /**
     * 场景调度
     * 
     * @param moduleName
     * @param taskId
     * @param command
     * @param roleId
     * @param stageId
     * @param data
     * @param delay
     * @param timeUnit
     */
    public void schedule(String moduleName, String taskId, String command, String roleId, String stageId, Object data, long delay, TimeUnit timeUnit);
    
    /**
     * 取消调度
     * 
     * @param moduleName
     * @param taskId
     */
    public void cancelSchedule(String moduleName, String taskId);

}
