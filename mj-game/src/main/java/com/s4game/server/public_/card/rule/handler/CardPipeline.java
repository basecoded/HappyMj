package com.s4game.server.public_.card.rule.handler;

import java.util.Map;
import java.util.Map.Entry;

/**
 * handler 处理链
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年10月12日 下午3:09:47
 */
public interface CardPipeline extends Iterable<Entry<String, CardHandler>> {

    CardPipeline addFirst(String name, CardHandler handler);
    
    CardPipeline addLast(String name, CardHandler handler);
    
    CardPipeline remove(CardHandler handler);
    
    CardHandler remove(String name);
    
    CardHandler removeFirst();
    
    CardHandler removeLast();
    
    CardHandlerContext context(CardHandler handler);
    
    Map<String, CardHandler> toMap();
}
