package com.s4game.server.share.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeusgooogle@gmail.com
 * @date 2015年05月07日 下午9:59:07
 */
public class EventHandleCommands {

	private List<Node> nodes;

	public EventHandleCommands() {
	}

	public EventHandleCommands add(String eventType, String command) {
		if (this.nodes == null) {
			this.nodes = new ArrayList<Node>();
		}
		this.nodes.add(new Node(eventType, command));
		return this;
	}

	public List<Node> nodes() {
		return this.nodes;
	}

	public class Node {
		private String eventType;
		private String command;

		public Node(String eventType, String command) {
			this.eventType = eventType;
			this.command = command;
		}

		public String getCommand() {
			return this.command;
		}

		public String getEventType() {
			return this.eventType;
		}
	}

}
