package cn.encmys.ykdz.forest.landsqqbot.enums;

public enum QQCommandArgs {
    LAND,
    PLAYER,
    AREA,
    NATION,
    ROLE,
    FLAG,
    ACTION,
    MEMBER_TYPE,
    AMOUNT;

    public static QQCommandArgs fromString(String name) {
        for (QQCommandArgs c : QQCommandArgs.values()) {
            if (c.name().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}