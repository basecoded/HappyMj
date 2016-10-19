package com.s4game.core.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月4日 上午11:23:25
 * 
 */
public class WebsocktListener {

    private Logger LOG = LoggerFactory.getLogger(getClass());

	private int port;

	private int bossThreadSize;

	private int workerThreadSize;

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	private ChannelInitializer<?> initializer;

	private boolean success = false;

	public void start() {
		bossGroup = new NioEventLoopGroup(getBossThreadSize());
		workerGroup = new NioEventLoopGroup(getWorkerThreadSize());

		ServerBootstrap bootstarp = new ServerBootstrap();
		bootstarp.group(bossGroup, workerGroup);
		bootstarp.channel(NioServerSocketChannel.class);

		bootstarp.childHandler(initializer);

		try {
			bootstarp.bind(port).sync();
		} catch (InterruptedException e) {
			LOG.error("server io fialed bind port: {}", port, e);

			e.printStackTrace();
		}

		LOG.info("server listene success. bind port: {}", port);

		this.success = true;
	}

	public void stop() {
		if (success) {
			this.bossGroup.shutdownGracefully();
			this.workerGroup.shutdownGracefully();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getBossThreadSize() {
		if (bossThreadSize <= 0) {
			throw new RuntimeException("bossThread must be more then 0");
		}
		return bossThreadSize;
	}

	public void setBossThreadSize(int bossThreadSize) {
		this.bossThreadSize = bossThreadSize;
	}

	public int getWorkerThreadSize() {
		if (workerThreadSize <= 0) {
			throw new RuntimeException("workerThreadSize must be more then 0");
		}
		return workerThreadSize;
	}

	public void setWorkerThreadSize(int workerThreadSize) {
		this.workerThreadSize = workerThreadSize;
	}

	public void setInitializer(ChannelInitializer<?> initializer) {
		this.initializer = initializer;
	}

}
