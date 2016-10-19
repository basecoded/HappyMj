package com.s4game.server.public_.room.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.public_.room.command.RoomCommands;
import com.s4game.server.public_.room.entity.Room;
import com.s4game.server.public_.room.output.RoomOutput;
import com.s4game.server.public_.room.service.IRoomService;
import com.s4game.server.public_.swap.PublicMsgSender;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月17日 下午12:07:31
 *
 */
@ActionWorker
public class RoomAction {

    private Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PublicMsgSender msgSender;
    
    @Autowired
    private IRoomService roomService;

    @ActionMapping(mapping = RoomCommands.CREATE_ROOM)
    public void create(Message message) {
        LOG.info(message.toString());

        JSONObject data = (JSONObject) message.getData();
        String roleId = message.getRoleId();
        
        int round = data.getIntValue("round");
        boolean serial = data.getBooleanValue("serial");
        boolean win = data.getBooleanValue("win");
        
        roomService.createRoom(roleId, round, serial, win);
        
    }
    
    @ActionMapping(mapping = RoomCommands.JOIN_ROOM)
    public void join(Message message) {
        JSONObject data = (JSONObject) message.getData();
        
        String roleId = message.getRoleId();
        String roomId = data.getString("roomId");
        
        roomService.enterRoom(roleId, roomId);
    }
}
