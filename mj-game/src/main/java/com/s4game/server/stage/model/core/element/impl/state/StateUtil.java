package com.s4game.server.stage.model.core.element.impl.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateUtil {
    
    private static Map<StateType, List<StateEventType>> forbiddens = new HashMap();
    private static Map<StateType, List<StateType>> execludes = new HashMap();

    public static List<StateType> getExculde(StateType paramStateType) {
        return (List) execludes.get(paramStateType);
    }

    public static boolean isForbidden(StateType paramStateType, StateEventType paramStateEventType) {
        List localList = (List) forbiddens.get(paramStateType);
        return (null != localList)
                && (((localList.contains(StateEventType.ALL_BUT_REVIVE)) && (!paramStateEventType.equals(StateEventType.REVIVE))) || (localList
                        .contains(paramStateEventType)));
    }

    static {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        localArrayList1 = new ArrayList();
        localArrayList1.add(StateEventType.ALL_BUT_REVIVE);
        forbiddens.put(StateType.DEAD, localArrayList1);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.FIGHT);
        localArrayList2.add(StateType.XUNLUO);
        execludes.put(StateType.BACK, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.FIGHT);
        localArrayList2.add(StateType.XUNLUO);
        execludes.put(StateType.STOP, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.XUNLUO);
        localArrayList2.add(StateType.STOP);
        localArrayList2.add(StateType.BACK);
        localArrayList2.add(StateType.DAZUO);
        execludes.put(StateType.FIGHT, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.BACK);
        localArrayList2.add(StateType.FIGHT);
        localArrayList2.add(StateType.STOP);
        execludes.put(StateType.XUNLUO, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.ZUOQI);
        localArrayList2.add(StateType.FIGHT);
        localArrayList2.add(StateType.GATHER);
        execludes.put(StateType.DAZUO, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.DAZUO);
        execludes.put(StateType.ZUOQI, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.FIGHT);
        localArrayList2.add(StateType.DAZUO);
        localArrayList2.add(StateType.ZUOQI);
        localArrayList2.add(StateType.GATHER);
        execludes.put(StateType.DEAD, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.ONLINE);
        execludes.put(StateType.OFFLINE, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.OFFLINE);
        execludes.put(StateType.ONLINE, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.GATHER);
        execludes.put(StateType.HUNMI, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.GATHER);
        execludes.put(StateType.JINGU, localArrayList2);
        localArrayList2 = new ArrayList();
        localArrayList2.add(StateType.GATHER);
        execludes.put(StateType.BINGDONG, localArrayList2);
        localArrayList1 = new ArrayList();
        localArrayList1.add(StateEventType.MOVE);
        localArrayList1.add(StateEventType.ATTACK);
        localArrayList1.add(StateEventType.CAIKUANG);
        forbiddens.put(StateType.KONGJU, localArrayList1);
        forbiddens.put(StateType.HUNMI, localArrayList1);
        forbiddens.put(StateType.BINGDONG, localArrayList1);
        forbiddens.put(StateType.SHIHUA, localArrayList1);
        forbiddens.put(StateType.MABI, localArrayList1);
        forbiddens.put(StateType.JINGU, localArrayList1);
        localArrayList1 = new ArrayList();
        localArrayList1.add(StateEventType.ATTACK);
        forbiddens.put(StateType.CHENMO, localArrayList1);
        forbiddens.put(StateType.BIAN_XING, localArrayList1);
        localArrayList1 = new ArrayList();
        localArrayList1.add(StateEventType.ATTACKED);
        forbiddens.put(StateType.WUDI, localArrayList1);
        localArrayList1 = new ArrayList();
        localArrayList1.add(StateEventType.MOVE);
        localArrayList1.add(StateEventType.ATTACKED);
        forbiddens.put(StateType.WUDI_NOMOVE, localArrayList1);
        localArrayList1 = new ArrayList();
        forbiddens.put(StateType.SATAGEPRETECT, localArrayList1);
        localArrayList1 = new ArrayList();
        localArrayList1.add(StateEventType.ZUOQI);
        forbiddens.put(StateType.PKFREE, localArrayList1);
 
    }
}
