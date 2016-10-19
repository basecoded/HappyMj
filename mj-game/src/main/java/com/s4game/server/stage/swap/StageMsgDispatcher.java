package com.s4game.server.stage.swap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.action.front.IActionFrontend;
import com.s4game.core.message.Message;
import com.s4game.server.executor.IBusinessExecutor;
import com.s4game.server.executor.IRunnable;
import com.s4game.server.executor.Route;
import com.s4game.server.executor.impl.RunnableImpl;
import com.s4game.server.message.IMsgDispatcher;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月9日 下午5:47:55
 * 
 */
@Component(value = "stageDispatcher")
public class StageMsgDispatcher implements IMsgDispatcher {

	private ThreadLocal<IRunnable> runnableLocal = new ThreadLocal<IRunnable>();

	private StageRouteHelpler routeHelper = new StageRouteHelpler();

	@Resource(name = "stageExecutor")
	private IBusinessExecutor businessExexutor;

	@Resource(name = "actionFrontend")
	private IActionFrontend actionFrontend;

	@Override
	public void in(Message message) {
		Runnable localRunnable = getRunnable().getRunnable(message);
		
		Route localRouteInfo = this.routeHelper.getRoute(message);
		this.businessExexutor.execute(localRunnable, localRouteInfo);

	}

	private IRunnable getRunnable() {
		IRunnable runnalbe = this.runnableLocal.get();
		if (null == runnalbe) {
			runnalbe = new RunnableImpl(this.actionFrontend);
			this.runnableLocal.set(runnalbe);
		}
		return runnalbe;
	}

}
