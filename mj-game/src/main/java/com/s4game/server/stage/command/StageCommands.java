package com.s4game.server.stage.command;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 下午3:44:47
 *
 */

public class StageCommands {
    
    public static final String SKILL_READY_FIRE = "42001";
    public static final String SKILL_FIRE = "42002";
    
    /**
     *  进入场景
     */
    public static final String INNER_ENTER_STAGE = "S:ENTER";

    /**
     * 退出场景
     */
    public static final String INNER_LEAVE_STAGE = "S:LEAVE";
    
    
    /**
     * 关卡超时检查
     * <p> ISingleStageCopy
     */
    public static final String CHECKPOINT_EXPIRE_CHECK = "S:EXPIRE_CHECK_COPY";
    
    /**
     * 关卡强制退出检查
     * <p> ISingleStageCopy
     */
    public static final String CHECKPOINT_FORCE_CHECK = "S:FORCE_CHECK_COPY";
    
    /**
     * 关卡挑战结果
     */
    public static final String CHECKPOINT_CHELLENGE_RESULT = "64003";
    
    /**
     * 关卡退出
     */
    public static final String CHECKPOINT_LEAVE = "64008";
}
