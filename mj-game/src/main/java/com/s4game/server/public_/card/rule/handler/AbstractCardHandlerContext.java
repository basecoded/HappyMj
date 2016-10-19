package com.s4game.server.public_.card.rule.handler;

public abstract class AbstractCardHandlerContext implements CardHandlerContext {

    volatile AbstractCardHandlerContext next;
    volatile AbstractCardHandlerContext prev;
    
    private final DefaultCardPipeline pipeline;
    private final String name;
    
    AbstractCardHandlerContext(DefaultCardPipeline pipeline, String name) {
        this.pipeline = pipeline;
        this.name = name;
    }
    
    @Override
    public CardPipeline pipeline() {
        return pipeline;
    }
    
    @Override
    public String name() {
        return name;
    }

}
