package com.s4game.server.login.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.login.output.InOutput;
import com.s4game.server.login.service.ILoginService;
import com.s4game.server.public_.swap.PublicMsgSender;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月7日 上午11:14:43
 *
 */
@ActionWorker
public class LoginAction {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private PublicMsgSender msgSender;

    @Autowired
    private ILoginService loginService;

    @ActionMapping(mapping = LoginCommands.IN)
    public void in(Message message) {
        LOG.info(message.toString());

        JSONObject data = (JSONObject) message.getData();
        String userId = data.getString("userId");
        String name = data.getString("name");
        String face = data.getString("face");
        String serverId = "1";

        
        String timestamp = System.currentTimeMillis() + "";
        String sign = "";

        UserRole userRole = loginService.in(userId, serverId, name, face, timestamp, sign);
        msgSender.send2OneBySessionId(message.getCommand(), userRole.getId(),
                message.getSessionId(), InOutput.success(userRole));
    }

}
