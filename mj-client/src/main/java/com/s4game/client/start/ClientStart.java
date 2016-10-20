package com.s4game.client.start;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.s4game.client.io.ClientListener;
import com.s4game.protocol.Message.Request;

public class ClientStart {

    public static final Logger LOG = LoggerFactory.getLogger(ClientStart.class);
    
    public static ApplicationContext ctx;
    
    public static void main(String[] args) throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        ClientListener listener = ctx.getBean(ClientListener.class);
        listener.start();
        
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msg = console.readLine();
            JSONObject json = JSONObject.parseObject(msg);

            Request.Builder request = Request.newBuilder();
            request.setCommand(json.getString("cmd"))
                   .setData(json.getString("data"));
            
            if (msg == null) {
                break;
            } else if ("exit".equals(msg.toLowerCase())) {
                listener.stop();
                break;
            } else {
                listener.sendMessage(request);
            }
        }
    }

}
