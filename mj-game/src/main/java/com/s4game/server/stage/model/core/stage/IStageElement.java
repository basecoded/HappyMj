package com.s4game.server.stage.model.core.stage;


/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月16日 下午3:01:41
 *
 */

public interface IStageElement extends IStageElementLifeCycle {

    public String getId();
    
    public String getName();
    
    public String getCamp();
    
    public String getTeamId();
    
    public ElementType getElementType();
    
    public Object getMsgData();
    
    public IStage getStage();
    
    public void setStage(IStage stage);
    
    public void setPosition(int x, int y);
    
    public void setBornPosition(int x, int y);
    
    public Point getPosition();
    
    public Point getBornPosition();
    
}
