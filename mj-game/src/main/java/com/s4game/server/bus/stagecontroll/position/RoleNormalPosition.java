package com.s4game.server.bus.stagecontroll.position;

import com.s4game.server.bus.map.MapType;
import com.s4game.server.stage.constants.StageCopyConstant;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月20日 上午10:54:27
 *
 */

public class RoleNormalPosition extends AbsRolePosition {

    public RoleNormalPosition(String roleId, String mapId, int x, int y) {
        super(roleId, mapId, MapType.IDLE, x, y);
    }

    @Override
    public String getStageId() {
        return StageCopyConstant.INIT_STAGE_PREFIX + getMapId();
    }

    @Override
    public Object[] enterPositionFormat() {
        return new Object[]{getStageId(), getMapId(), getX(), getY()};
    }

    @Override
    public boolean isCopyMap() {
        return false;
    }

}
