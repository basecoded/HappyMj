package com.s4game.server.stage.model.core.element.impl.state;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.s4game.server.stage.model.core.element.IState;
import com.s4game.server.stage.model.core.element.IStateManager;

public class StateManager implements IStateManager {
    
    private Map<StateType, IState> stateMap = new HashMap();
    
    private StateStatistic stateStatistic;
    
    public static final Integer LIVE = Integer.valueOf(0);
    public static final Integer DEAD = Integer.valueOf(2);
    public static final Integer DAZUO = Integer.valueOf(20);
    public static final Integer GATHER = Integer.valueOf(30);

    public void add(IState state) {
        if (null == getState(state.getType())) {
            List exculdeList = StateUtil.getExculde(state.getType());
            if (null != exculdeList) {
                
                Iterator<StateType> iterator = exculdeList.iterator();
                while (iterator.hasNext()) {
                    StateType stateType = iterator.next();
                    
                    IState exculdeState = getState(stateType);
                    if (null != exculdeState) {
                        exculdeState.setValid(false);
                        if (exculdeState.immediateReplace()) {
                            this.stateMap.remove(stateType);
                        }
                        _getStateStatistic().replaceState(exculdeState);
                    }
                }
            }
            this.stateMap.put(state.getType(), state);
            _getStateStatistic().addState(state);
        }
    }

    public IState getState(StateType stateType) {
        if (null != stateMap) {
            return stateMap.get(stateType);
        }
        return null;
    }

    public boolean remove(StateType stateType) {
        IState localIState = getState(stateType);
        if (null != localIState) {
            this.stateMap.remove(stateType);
            _getStateStatistic().removeState(localIState);
            return true;
        }
        return false;
    }

    public boolean isForbidden(StateEventType stateEventType) {
        if (null != this.stateMap) {
            Iterator<StateType> iterator = this.stateMap.keySet().iterator();
            while (iterator.hasNext()) {
                StateType stateType = iterator.next();
                
                if (StateUtil.isForbidden(stateType, stateEventType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(StateType stateType) {
        return null != getState(stateType);
    }

    public boolean isDead() {
        return (null != stateMap) && (stateMap.containsKey(StateType.DEAD));
    }

    private void clearAll() {
        if (null != this.stateMap) {
            Iterator<StateType> iterator = this.stateMap.keySet().iterator();
            while (iterator.hasNext()) {
                StateType stateType = iterator.next();
                this.stateMap.get(stateType);
                
                iterator.remove();
            }
        }
    }

    public Integer getClientState() {
        if (null != getState(StateType.DEAD)) {
            return DEAD;
        }
        if (null != getState(StateType.DAZUO)) {
            return DAZUO;
        }
        if (null != getState(StateType.GATHER)) {
            return GATHER;
        }
        return LIVE;
    }

    public void clear() {
        clearAll();
    }

    public IState remove2(StateType stateType) {
        IState localIState = getState(stateType);
        if (null != localIState) {
            localIState = (IState) this.stateMap.remove(stateType);
            _getStateStatistic().removeState(localIState);
            return localIState;
        }
        return null;
    }

    private StateStatistic _getStateStatistic() {
        if (null == this.stateStatistic) {
            this.stateStatistic = new StateStatistic();
        }
        return this.stateStatistic;
    }

    public StateStatistic getStateStatistic() {
        StateStatistic localStateStatistic = this.stateStatistic;
        this.stateStatistic = null;
        return localStateStatistic;
    }
}
