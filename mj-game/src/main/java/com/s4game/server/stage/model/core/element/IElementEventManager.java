package com.s4game.server.stage.model.core.element;

public interface IElementEventManager {
    
    public static final String EVENTTYPE_LOGIN = "LOGIN";
    public static final String EVENTTYPE_LOGOUT = "LOGOUT";
    public static final String EVENTTYPE_MOVE = "MOVE";
    public static final String EVENTTYPE_ATTACKED = "ATTACKED";
    public static final String EVENTTYPE_FINDELEMENT = "FINDELEMENT";

    public void fireLoginEvent();

    public void fireLogoutEvent();

    public void fireMoveEvent();


    public void leaveFightStateEvent();

    public void fireStateChange();
}