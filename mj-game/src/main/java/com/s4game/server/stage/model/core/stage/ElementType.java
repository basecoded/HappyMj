package com.s4game.server.stage.model.core.stage;

public enum ElementType {

    ROLE(1),

    MONSTER(2),

    NPC(3),

    PET(4),

    GOODS(5),

    DIAOXIANG(6),

    FIGHTER(100),

    ;

    private Integer val;

    private ElementType(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return this.val;
    }

    public static boolean isRole(ElementType type) {
        return ROLE.equals(type);
    }

    public static boolean isMonster(ElementType type) {
        return MONSTER.equals(type);
    }

    public static boolean isNpc(ElementType type) {
        return NPC.equals(type);
    }

    public static boolean isFighter(ElementType type) {
        return (ROLE.equals(type)) || (MONSTER.equals(type)) || (PET.equals(type));
    }

    public static boolean isGoods(ElementType type) {
        return GOODS.equals(type);
    }

    public static boolean isDiaoXiang(ElementType type) {
        return DIAOXIANG.equals(type);
    }

    public static boolean isPet(ElementType type) {
        return PET.equals(type);
    }

    public static ElementType convert(Integer val) {
        if (ROLE.getVal().equals(val)) {
            return ROLE;
        }
        if (MONSTER.getVal().equals(val)) {
            return MONSTER;
        }
        if (NPC.getVal().equals(val)) {
            return NPC;
        }
        if (PET.getVal().equals(val)) {
            return PET;
        }

        if (DIAOXIANG.getVal().equals(val)) {
            return DIAOXIANG;
        }

        throw new NullPointerException("no ElementType for this val :" + val);
    }
}