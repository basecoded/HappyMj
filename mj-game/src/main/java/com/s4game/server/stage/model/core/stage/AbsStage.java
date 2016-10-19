package com.s4game.server.stage.model.core.stage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午3:18:34
 * 
 */

public abstract class AbsStage implements IStage {

    private Map<ElementType, Map<String, IStageElement>> elementMaps = new HashMap<>();

    @Override
    public void enter(IStageElement stageElement, int x, int y) {
        Map<String, IStageElement> map = getElementMap(stageElement.getElementType());
        map.put(stageElement.getId(), stageElement);

        stageElement.setStage(this);
        stageElement.setPosition(x, y);
        stageElement.setBornPosition(x, y);

        addHandler(stageElement);
    }

    @Override
    public void leave(IStageElement stageElement) {
        Map<String, IStageElement> map = getElementMap(stageElement.getElementType());
        IStageElement element = map.get(stageElement.getId());
        if (null != element) {
            deleteHandler(element);

            element.setStage(null);
            map.remove(element.getId());
        }
    }

    @Override
    public void moveTo(IStageElement stageElement, int x, int y) {
        
    }

    protected Map<String, IStageElement> getElementMap(ElementType elementType) {
        Map<String, IStageElement> map = this.elementMaps.get(elementType);
        if (null == map) {
            map = new HashMap<String, IStageElement>();
            this.elementMaps.put(elementType, map);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IStageElement> T getElement(String elementId, ElementType elementType) {
        return (T) getElementMap(elementType).get(elementId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IStageElement> Collection<T> getElementsByType(ElementType elementType) {
        return (Collection<T>) getElementMap(elementType).values();
    }

    protected abstract void addHandler(IStageElement stageElement);

    protected abstract void deleteHandler(IStageElement stageElement);

}
