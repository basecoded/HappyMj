package com.s4game.server.public_.card.rule.handler;

public interface CardHandlerContext {

    String name();
    
    CardHandler handler();
    
    CardPipeline pipeline();
    
}
