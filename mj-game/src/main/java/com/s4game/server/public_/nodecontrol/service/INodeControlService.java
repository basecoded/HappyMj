package com.s4game.server.public_.nodecontrol.service;

public interface INodeControlService {
    
    void change2online(String roleId);

    void change2offline(String roleId);

    void nodeLogin(String roleId, String ip);

    void nodeExit(String roleId, String paramString2);

    void nodeExitOnServerClose(String roleId);
}
