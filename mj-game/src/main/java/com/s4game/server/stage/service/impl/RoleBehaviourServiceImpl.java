package com.s4game.server.stage.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.model.element.role.Role;
import com.s4game.server.stage.service.IRoleBehaviourService;
import com.s4game.server.stage.service.IStageService;

@Service
public class RoleBehaviourServiceImpl implements IRoleBehaviourService {

    @Autowired
    private IStageService stageService;
    
    @Override
    public String[] getStageRoleId(String stageId) {
        IStage stage = stageService.getStage(stageId);
        
        Collection<Role> roles = stage.getElementsByType(ElementType.ROLE);
        List<String> roleIds = new ArrayList<>(roles.size());
        for (Role r : roles) {
            roleIds.add(r.getId());
        }
        
        return roleIds.toArray(new String[0]);
    }

}
