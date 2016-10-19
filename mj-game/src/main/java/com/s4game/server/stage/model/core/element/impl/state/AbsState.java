package com.s4game.server.stage.model.core.element.impl.state;

import com.s4game.server.stage.model.core.element.IState;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月21日 下午12:20:19
 *
 */

public abstract class AbsState implements IState {

    private Object data;
    private boolean valid;

    @Override
    public <T> T getData() {
        return (T) data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean immediateReplace() {
        return true;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
