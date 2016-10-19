package com.s4game.server.bus.stagecontroll.service;

import com.s4game.server.bus.stagecontroll.position.AbsRolePosition;
import com.s4game.server.bus.stagecontroll.position.RoleLocation;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月2日 下午5:01:16
 *
 */

public interface IStageControllService {

    public Object login(String roleId);
    
    /**
     * 客户端申请进入地图
     * 
     * @param roleId
     * @return
     */
    public Object[] applyChangeMapAfterLogin(String roleId);
    
    public Object logout(String roleId);
    
    /**
     * 角色是否在线
     * 
     * @param roleId
     * @return
     */
    public boolean isOnline(String roleId);
    
    /**
     * 角色是否在副本中
     * 
     * @param roleId
     * @return
     */
    public boolean isInCopy(String roleId);
    
    public void changeMap(String roleId);
    
    
    public AbsRolePosition getOfflineSaveMapPosition(String roleId);
   
    /**
     * 获取Role 当前地图所在坐标
     * 
     * @param roleId
     * @return
     */
    public RoleLocation getHisMapPosition(String roleId);
    
    public void serverStartInitStage();
    
    /**
     * 请求进入副本
     * 
     * <p> 普通副本
     * 
     * @param roleId
     * @param mapId
     * @return
     */
    public Object applyChangeCopy(String roleId, String mapId, int x, int y, Object[] additionalData);
}
