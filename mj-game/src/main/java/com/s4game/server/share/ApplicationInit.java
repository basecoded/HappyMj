package com.s4game.server.share;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月6日 上午10:31:03
 * 
 */

public class ApplicationInit {

	private static ApplicationContext ctx;

	public static void init() {
		initSpringContext();
	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	
	private static void initSpringContext() {
		ctx = new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
	}

}
