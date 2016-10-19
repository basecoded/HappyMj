package com.s4game.server.io.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.message.Message;
import com.s4game.core.message.Message.DestType;
import com.s4game.core.message.Message.FromType;
import com.s4game.protocol.Message.Request;
import com.s4game.server.io.IoConstants;
import com.s4game.server.io.global.ChannelManager;
import com.s4game.server.io.swap.IoMsgSender;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.public_.nodecontrol.command.NodeControlCommands;
import com.s4game.server.utils.ChannelAttributeUtil;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class MessageHandler extends SimpleChannelInboundHandler<Request> {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChannelManager channelManager;

    @Autowired
    private IoMsgSender msgSender;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOG.info("channel active: " + ctx.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);

        if (null != roleId) {
            channelManager.removeChannel(roleId);

            exitNotify(ctx);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        JSONObject json = JSONObject.parseObject(msg.getData());
        
        String command = msg.getCommand();
        String sessionId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.SESSION_KEY);
        switch (command) {
        case LoginCommands.IN:
            String userId = json.getString("userId");
            ChannelAttributeUtil.attr(ctx.channel(), IoConstants.USER_KEY, userId);

            channelManager.addChannel(sessionId, ctx.channel());
            break;
        case LoginCommands.LOGIN_IN:
            command = NodeControlCommands.ROLE_IN;

            String roleId = json.getString("roleId");
            ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY, roleId);

            channelManager.addChannel(roleId, ctx.channel());
            break;
        }

        String ip = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.IP_KEY);
        String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);
        String userId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.USER_KEY);

        Message message = new Message(command, json, FromType.CLIENT, DestType.BUS, roleId, userId,
                sessionId, ip);
        msgSender.swap(message);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);

        if (null != roleId) {
            LOG.info("role: {} disconnect.", roleId);
        }
    }

    /**
     * 退出服务
     * 
     * @param ctx
     * @throws Exception
     */
    private void exitNotify(ChannelHandlerContext ctx) throws Exception {
        String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);

        JSONObject json = new JSONObject();
        json.put("cmd", NodeControlCommands.ROLE_OUT);
        json.put("roleId", roleId);

        Request.Builder exit = Request.newBuilder();
        exit.setCommand(NodeControlCommands.ROLE_OUT)
            .setData(json.toJSONString());
        
        channelRead0(ctx, exit.build());
    }
}
