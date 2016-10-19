package com.s4game.server.executor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.action.front.IActionFrontend;
import com.s4game.core.message.Message;
import com.s4game.server.executor.IRunnable;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月6日 下午4:04:17
 *
 */

public class RunnableImpl implements IRunnable {

	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	private IActionFrontend actionFrontend;
	
	public RunnableImpl(IActionFrontend actionFrontend) {
		this.actionFrontend = actionFrontend;
	}
	
	@Override
	public Runnable getRunnable(Message message) {
		return new RunnableTask(message);
	}
	
	private class RunnableTask implements Runnable {

		private Message message;
		
		public RunnableTask(Message message) {
			if (null == message) {
                throw new NullPointerException("message can't be null.");
            }
			
			this.message = message;
		}
		
		@Override
		public void run() {
			try {
				actionFrontend.execute(message.getCommand(), message);
			} catch(Exception e) {
				LOG.error("message: {}", message, e);
			} finally {
				message = null;
			}
		}
	}
}
