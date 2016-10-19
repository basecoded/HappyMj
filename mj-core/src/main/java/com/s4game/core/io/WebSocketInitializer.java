package com.s4game.core.io;

import java.util.List;

import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

	private static final String WEBSOCKET_PATH = "/websocket";

	private List<ChannelInboundHandler> handlers;

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));

		for (ChannelInboundHandler h : handlers) {
			pipeline.addLast("handler" + h.getClass().getSimpleName(), h);
		}
	}

	public void setHandlers(List<ChannelInboundHandler> handlers) {
		this.handlers = handlers;
	}

}
