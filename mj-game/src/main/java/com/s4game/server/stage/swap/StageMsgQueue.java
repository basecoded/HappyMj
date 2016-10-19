package com.s4game.server.stage.swap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StageMsgQueue {

    private StageMsgSender stageMsgSender;

    private List<IMsg> msgs = new ArrayList();

    private boolean fifiOrLiFi = true;

    public StageMsgQueue(StageMsgSender stageMsgSender) {
        this.stageMsgSender = stageMsgSender;
    }

    private void addMsg(IMsg paramIMsg) {
        if ((!this.fifiOrLiFi) && (this.msgs.size() > 0)) {
            this.msgs.add(0, paramIMsg);
        } else {
            this.msgs.add(paramIMsg);
        }
    }

    public void flush() {
        if (this.msgs.size() > 0) {
            Iterator localIterator = this.msgs.iterator();
            while (localIterator.hasNext()) {
                IMsg localIMsg = (IMsg) localIterator.next();
                localIMsg.flush();
            }
        }
    }

    public void flushAndRemove() {
        if (this.msgs.size() > 0) {
            Iterator localIterator = this.msgs.iterator();
            while (localIterator.hasNext()) {
                IMsg localIMsg = (IMsg) localIterator.next();
                localIMsg.flush();
            }
        }
        this.msgs.clear();
    }

    public void addMsg(String command, String roleId, Object data) {
        addMsg(new ClientMsg(command, roleId, data));
    }

    public void addMsg(String[] paramArrayOfString, String paramString, Object paramObject) {
        addMsg(new MultiClientMsg(paramArrayOfString, paramString, paramObject));
    }

    public void addStageMsg(String paramString1, String paramString2, String paramString3, Object paramObject) {
        addMsg(new StageMsg(paramString1, paramString2, paramString3, paramObject));
    }

    public void addInnerBusMsg(String paramString1, String paramString2, Object paramObject) {
        addMsg(new BusMsg(paramString1, paramString2, paramObject));
    }

    public void addBroadcastMsg(String paramString, Object paramObject) {
        addMsg(new BroadcastMsg(paramString, paramObject));
    }

    public void addStageControllMsg(String command, String roleId, Object data) {
        addMsg(new StageControllMsg(command, roleId, data));
    }

    public void orderRule(boolean paramBoolean) {
        this.fifiOrLiFi = false;
    }

    private static abstract interface IMsg {
        public abstract void flush();
    }

    private class ClientMsg implements StageMsgQueue.IMsg {
        private String roleId;
        private String command;
        private Object data;

        public ClientMsg(String command, String roleId, Object data) {
            this.command = command;
            this.roleId = roleId;
            this.data = data;
        }

        public void flush() {
            stageMsgSender.send2One(command, roleId, null, data);
        }
    }

    private static class StageMsg implements StageMsgQueue.IMsg {
        private String roleId;
        private String stageId;
        private String command;
        private Object result;

        public StageMsg(String paramString1, String paramString2, String paramString3, Object paramObject) {
            this.roleId = paramString1;
            this.command = paramString3;
            this.result = paramObject;
            this.stageId = paramString2;
        }

        public void flush() {
            // StageMsgSender.send2StageInner(this.roleId, this.stageId,
            // this.command, this.result);
        }
    }

    private static class BusMsg implements StageMsgQueue.IMsg {
        private String roleId;
        private String command;
        private Object result;

        public BusMsg(String paramString1, String paramString2, Object paramObject) {
            this.roleId = paramString1;
            this.command = paramString2;
            this.result = paramObject;
        }

        public void flush() {
            // StageMsgSender.send2Bus(this.roleId, this.command, this.result);
        }
    }

    private static class MultiClientMsg implements StageMsgQueue.IMsg {
        private String[] roleIds;
        private String command;
        private Object result;

        public MultiClientMsg(String[] paramArrayOfString, String paramString, Object paramObject) {
            this.roleIds = paramArrayOfString;
            this.command = paramString;
            this.result = paramObject;
        }

        public void flush() {
            // StageMsgSender.send2Many(this.roleIds, this.command,
            // this.result);
        }
    }

    private static class BroadcastMsg implements StageMsgQueue.IMsg {
        private String command;
        private Object result;

        public BroadcastMsg(String paramString, Object paramObject) {
            this.command = paramString;
            this.result = paramObject;
        }

        public void flush() {
            // StageMsgSender.send2All(this.command, this.result);
        }
    }

    private class StageControllMsg implements IMsg {

        private String roleId;
        private String command;
        private Object data;

        public StageControllMsg(String command, String role, Object data) {
            this.command = command;
            this.roleId = role;
            this.data = data;
        }

        public void flush() {
            stageMsgSender.send2StageControl(command, roleId, data);
        }
    }
}
