package cn.encmys.ykdz.forest.landsqqbot.enums;

public enum MemberType {
    LAND,
    AREA,
    PLAYER,
    ALLY;

    public static MemberType fromString(String name) {
        name = name.replaceAll("-", "_");
        for (MemberType c : MemberType.values()) {
            if (c.name().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

}
