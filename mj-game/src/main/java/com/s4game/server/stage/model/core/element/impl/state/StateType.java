package com.s4game.server.stage.model.core.element.impl.state;

public enum StateType {
    
    OFFLINE(Integer.valueOf(-1)), 
    
    ONLINE(Integer.valueOf(-2)), 
    
    LIVE(Integer.valueOf(0)), 
    
    DEAD(Integer.valueOf(2)), 
    
    XUNLUO(Integer.valueOf(3)), 
    
    BACK(Integer.valueOf(4)), 
    
    FIGHT(Integer.valueOf(5)), 
    
    DAZUO(Integer.valueOf(11)), 
    
    ZUOQI(Integer.valueOf(12)), 
    
    KONGJU(Integer.valueOf(21)), 
    
    HUNMI(Integer.valueOf(22)), 
    
    BINGDONG(Integer.valueOf(23)), 
    
    SHIHUA(Integer.valueOf(24)), 
    
    MABI(Integer.valueOf(25)), 
    
    JINGU(Integer.valueOf(26)), 
    
    JITUI(Integer.valueOf(27)), 
    
    WUDI_NOMOVE(Integer.valueOf(28)), 
    
    BIAN_XING(Integer.valueOf(29)), 
    
    CHENMO(Integer.valueOf(31)), 
    
    WUDI(Integer.valueOf(32)), 
    
    SATAGEPRETECT(Integer.valueOf(33)), 
    
    PKFREE(Integer.valueOf(34)), 
    
    LINGJIEHUDUN(Integer.valueOf(36)), 
 
    GATHER(Integer.valueOf(57)),  
    
    JUMP(Integer.valueOf(58)),
    
    STOP(Integer.valueOf(59)),
    
    ;

    public final Integer val;

    private StateType(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return this.val;
    }
}
