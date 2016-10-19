package com.s4game.core.action.resolver;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年4月26日 下午6:26:57
 * 
 */
public class DefaultActionResolver implements IActionResolver {

	private final static Logger LOG = LoggerFactory.getLogger(DefaultActionResolver.class);

	private Method m;
	private Object target;

	public DefaultActionResolver(Method m, Object target) {
		this.m = m;
		this.target = target;
	}

	@Override
	public void execute(Object message) {
		try {

			// invoke
			m.invoke(target, new Object[] { message });

		} catch (Exception e) {
			LOG.error("", e);
			e.printStackTrace();
		}
	}
}
