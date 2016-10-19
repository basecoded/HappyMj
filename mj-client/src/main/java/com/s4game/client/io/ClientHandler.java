package com.s4game.client.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.protocol.Message.Response;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月4日 上午10:48:43
 *
 */
@Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Response> {

    private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
		LOG.info("client receive msg: {}", msg.toString());
	}

}
