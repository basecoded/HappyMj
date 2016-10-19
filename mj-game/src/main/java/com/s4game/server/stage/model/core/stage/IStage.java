package com.s4game.server.stage.model.core.stage;

import java.util.Collection;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午2:55:49
 * 
 */
public interface IStage {

    public String getId();

    public String getMapId();

    public boolean isCopy();
    
    public void enter(IStageElement stageElement, int x, int y);

    public void leave(IStageElement stageElement);

    public void moveTo(IStageElement stageElement, int x, int y);

    public <T extends IStageElement> T getElement(String elementId, ElementType elementType);

    public <T extends IStageElement> Collection<T> getElementsByType(ElementType elementType);
}
