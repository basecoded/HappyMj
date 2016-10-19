package com.s4game.client.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.s4game.client.io.ClientListener;

public class StartClient {

    public static final Logger LOG = LoggerFactory.getLogger(StartClient.class);
    
    public static ApplicationContext ctx;
    
    public static void main(String[] args) throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        ClientListener listener = ctx.getBean(ClientListener.class);
        listener.start();
        
        
        
        Thread.sleep(Integer.MAX_VALUE);
    }

}
