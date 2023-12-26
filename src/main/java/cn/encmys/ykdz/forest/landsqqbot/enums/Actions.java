package cn.encmys.ykdz.forest.landsqqbot.enums;

import cn.encmys.ykdz.forest.landsqqbot.api.action.Action;

public enum Actions {
    PRINT_BAL,
    PRINT_BASIC_INFO,
    PRINT_INBOX,
    PRINT_MEMBERS;

    public static Actions fromString(String name) {
        name = name.replaceAll("-", "_");
        for (Actions c : Actions.values()) {
            if (c.name().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}
