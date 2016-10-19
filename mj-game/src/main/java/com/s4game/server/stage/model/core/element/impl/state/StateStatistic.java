package com.s4game.server.stage.model.core.element.impl.state;

import java.util.ArrayList;
import java.util.List;

import com.s4game.server.stage.model.core.element.IState;

public class StateStatistic {
    
    private List<IState> addList = new ArrayList();
    
    private List<IState> replaceList = new ArrayList();
    
    private boolean enterFightState = false;

    public void addState(IState state) {
        this.addList.add(state);
        if (StateType.FIGHT.equals(state.getType())) {
            this.enterFightState = true;
        }
    }

    public void replaceState(IState state) {
        if (this.addList.contains(state)) {
            this.addList.remove(state);
        } else {
            this.replaceList.add(state);
        }
    }

    public void removeState(IState state) {
        if (this.addList.contains(state)) {
            this.addList.remove(state);
        }
    }

    public void clear() {
        this.enterFightState = false;
        if (this.addList != null) {
            this.addList.clear();
        }
    }

    public boolean isEnterFightState() {
        return this.enterFightState;
    }

    public List<IState> getAddStates() {
        return this.addList;
    }

    public List<IState> getReplaceList() {
        return this.replaceList;
    }
}