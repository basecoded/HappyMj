package com.s4game.server.stage.model.core.element.impl;

import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.model.core.stage.IStageElement;
import com.s4game.server.stage.model.core.stage.Point;

public abstract class AbsElement implements IStageElement {
    
    private IStage stage;
    
    private Point position;
    private Point bornPosition;
    private String id;
    private String name;
    private String teamId;
    private String camp;

    public AbsElement(String id, String name, String teamId, String camp) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.camp = camp;
    }

    public String getId() {
        return this.id;
    }

    public IStage getStage() {
        return this.stage;
    }

    public void setStage(IStage stage) {
        this.stage = stage;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return this.position;
    }

    public Point getBornPosition() {
        return this.bornPosition;
    }

    public void setBornPosition(int x, int y) {
        this.bornPosition = new Point(x, y);
    }

    public String getName() {
        return this.name;
    }

    public String getCamp() {
        return this.camp;
    }

    public String getTeamId() {
        return this.teamId;
    }
}