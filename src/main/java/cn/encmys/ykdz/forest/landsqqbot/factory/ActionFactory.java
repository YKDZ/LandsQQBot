package cn.encmys.ykdz.forest.landsqqbot.factory;

import cn.encmys.ykdz.forest.landsqqbot.action.PrintBal;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintBasicInfo;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintInbox;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintMembers;
import cn.encmys.ykdz.forest.landsqqbot.api.action.Action;
import cn.encmys.ykdz.forest.landsqqbot.enums.Actions;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ActionFactory {
    public static @Nullable Action build(Actions action, Object target) {
        if(target instanceof Land) {
            return build(action, (Land) target);
        } else if(target instanceof Nation) {
            return build(action, (Nation) target);
        } else if(target instanceof Area) {
            return build(action, (Area) target);
        }
        return null;
    }

    public static @Nullable Action build(Actions action, Object target, MemberType memberType) {
        if(target instanceof Land) {
            return build(action, (Land) target, memberType);
        } else if(target instanceof Nation) {
            return build(action, (Nation) target, memberType);
        } else if(target instanceof Area) {
            return build(action, (Area) target, memberType);
        }
        return null;
    }

    public static @Nullable Action build(Actions action, Object target, int amount) {
        if(target instanceof Land) {
            return build(action, (Land) target, amount);
        } else if(target instanceof Nation) {
            return build(action, (Nation) target, amount);
        } else if(target instanceof Area) {
            return build(action, (Area) target, amount);
        }
        return null;
    }

    public static @Nullable Action build(@NotNull Actions action, Land land) {
        switch(action) {
            case PRINT_BAL:
                return new PrintBal(land);
            case PRINT_BASIC_INFO:
                return new PrintBasicInfo(land);
        }
        return null;
    }

    public static @Nullable Action build(@NotNull Actions action, Nation nation) {
        switch(action) {
            case PRINT_BAL:
                return new PrintBal(nation);
            case PRINT_BASIC_INFO:
                return new PrintBasicInfo(nation);
        }
        return null;

    }

    public static @Nullable Action build(@NotNull Actions action, Area area) {
        switch(action) {
            case PRINT_BAL:
                return new PrintBal(area);
            case PRINT_BASIC_INFO:
                return new PrintBasicInfo(area);
        }
        return null;
    }

    public static @Nullable Action build(@NotNull Actions action, Land land, MemberType memberType) {
        switch(action) {
            case PRINT_MEMBERS:
                return new PrintMembers(land, memberType);
        }
        return null;
    }

    public static @Nullable Action build(@NotNull Actions action, Nation nation, MemberType memberType) {
        switch(action) {
            case PRINT_MEMBERS:
                return new PrintMembers(nation, memberType);
        }
        return null;

    }

    public static @Nullable Action build(@NotNull Actions action, Area area, MemberType memberType) {
        switch(action) {
            case PRINT_MEMBERS:
                return new PrintMembers(area, memberType);
        }
        return null;
    }

    public static @Nullable Action build(@NotNull Actions action, Land land, int amount) {
        switch(action) {
            case PRINT_INBOX:
                return new PrintInbox(land, amount);
        }
        return null;
    }

    public static @Nullable Action build(@NotNull Actions action, Nation nation, int amount) {
        switch(action) {
            case PRINT_INBOX:
                return new PrintInbox(nation, amount);
        }
        return null;

    }

    public static @Nullable Action build(@NotNull Actions action, Area area, int amount) {
        switch(action) {
            case PRINT_INBOX:
                return new PrintInbox(area, amount);
        }
        return null;
    }

}
