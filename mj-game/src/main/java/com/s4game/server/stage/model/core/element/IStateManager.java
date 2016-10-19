package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.element.impl.state.StateEventType;
import com.s4game.server.stage.model.core.element.impl.state.StateStatistic;
import com.s4game.server.stage.model.core.element.impl.state.StateType;


/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午3:54:57
 * 
 */

public interface IStateManager extends IElementComponent {

    public abstract void add(IState state);

    public abstract IState getState(StateType stateType);

    public abstract boolean remove(StateType stateType);

    public abstract IState remove2(StateType stateType);

    public abstract boolean isForbidden(StateEventType stateEventType);

    public abstract boolean contains(StateType stateType);

    public abstract boolean isDead();

    public abstract Integer getClientState();

    public abstract void clear();

    public abstract StateStatistic getStateStatistic();

}
