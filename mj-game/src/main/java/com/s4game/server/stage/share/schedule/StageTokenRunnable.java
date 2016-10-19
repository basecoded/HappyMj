package com.s4game.server.stage.share.schedule;

import com.s4game.core.token.ITokenRunnable;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年9月8日 上午11:58:52
 * 
 */

public class StageTokenRunnable implements ITokenRunnable {

    private String moduleName;
    private String roleId;
    private String stageId;
    private String command;

    private Object data;
    private Object[] token;

    private boolean stageCommand;

    public StageTokenRunnable(String moduleName, String command, Object data) {
        this.moduleName = moduleName;
        this.command = command;
        this.data = data;
    }

    public StageTokenRunnable(String moduleName, String roleId, String stageId, String command, Object data) {
        if (null == roleId) {
            throw new IllegalArgumentException("roleId must input");
        }
        
        if (null == stageId) {
            throw new IllegalArgumentException("stageId must input");
        }

        this.moduleName = moduleName;
        this.roleId = roleId;
        this.stageId = stageId;
        this.command = command;
        this.data = data;

        this.stageCommand = true;
    }

    @Override
    public void run() {
        // TODO 发送协议

    }

    @Override
    public void setToken(Object[] token) {
        this.token = token;
    }

}
