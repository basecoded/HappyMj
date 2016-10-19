package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.stage.IStageElement;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午3:53:43
 *
 */

public interface IElement extends IStageElement {

    IStateManager getStateManager();

    IElementEventManager getEventManager();

}
