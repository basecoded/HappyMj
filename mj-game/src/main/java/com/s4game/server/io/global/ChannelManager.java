package com.s4game.server.io.global;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月5日 下午4:11:04
 *
 */
@Component
public class ChannelManager {
	
	private int maxCount = 30000;
	
	/**
	 * key  :	role_id / sessionId
	 * value:	Channel
	 */
	private final ConcurrentMap<String, Channel> sessions = new ConcurrentHashMap<String, Channel>();

	/**
	 * ip blacklist
	 */
	private Set<String> blacklist = new HashSet<String>();
	
	private ChannelManager() {
		
	}
	
	public void addChannel(String key, Channel channel) {
		sessions.put(key, channel);
	}
	
	public void removeChannel(String key) {
		sessions.remove(key);
	}
	
	public Channel getChannel(String key) {
		return sessions.get(key);
	}
	
	public int getSessionCount() {
		return sessions.size();
	}

	public ConcurrentMap<String, Channel> getSessions() {
		return sessions;
	}

	public Collection<String> getOnlineRoleIds() {
		return sessions.keySet();
	}
	
	public boolean isFull() {
		return getSessionCount() >= maxCount;
	}
	
	public boolean isBlackIp(String ip) {
		return blacklist.contains(ip);
	}

}
