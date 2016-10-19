package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.element.impl.state.StateType;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午4:01:53
 * 
 */

public interface IState {

    public abstract StateType getType();

    public abstract <T> T getData();

    public abstract void setData(Object data);

    public abstract boolean immediateReplace();

    public abstract boolean isValid();

    public abstract void setValid(boolean valid);

}
