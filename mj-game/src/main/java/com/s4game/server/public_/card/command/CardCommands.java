package com.s4game.server.public_.card.command;
/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午12:04:28 
*
*/
public class CardCommands {
    
	/**
	 * 初始化牌
	 */
    public static final String CARD_INIT = "3001";
    
    /**
     * 出牌
     */
    public static final String CARD_PLAY = "3002";
    
    /**
     * 吃，碰，过
     * 
     *  跑，提龙，胡牌，服务端自动处理
     */
    public static final String CARD_ACTION = "3003";
    
}
