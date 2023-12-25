package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.enums.ActionTargets;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.util.MessageUtils;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;

import java.util.*;

public class PrintMembers {
    private static final String messagePath = "print-player-members";
    private MemberType memberType;
    private ActionTargets targetType;
    private Object target;

    public PrintMembers(Object target, MemberType memberType) {
        this.target = target;
        this.memberType = memberType;
        if (target instanceof Land) {
            this.targetType = ActionTargets.LAND;
        } else if (target instanceof Area) {
            this.targetType = ActionTargets.AREA;
        } else if (target instanceof Nation) {
            this.targetType = ActionTargets.NATION;
        } else {
            this.targetType = ActionTargets.NULL;
        }
    }

    public String getResult() {
        if (targetType == ActionTargets.NULL) {
            return "你查找的对象不存在";
        }

        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", getTypeName());
            put("player-amount", getMembersAmount());
            put("name", getName());
            put("player", getPlayerMembers());
        }};

        return MessageUtils.joinList(MessageUtils.parseVariables(messages, args, getMembersAmount()));
    }

    public String getTypeName() {
        switch (this.targetType) {
            case LAND:
                return MessageConfigManager.getProperNoun("land");
            case NATION:
                return MessageConfigManager.getProperNoun("nation");
            case AREA:
                return MessageConfigManager.getProperNoun("area");
            default:
                return null;
        }
    }

    public String getName() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getName();
            case NATION:
                return ((Nation) this.target).getName();
            case AREA:
                return ((Area) this.target).getName();
            default:
                return null;
        }
    }

    public ArrayList<UUID> getPlayerMembers() {
        switch (this.targetType) {
            case LAND:
                return (ArrayList<UUID>) ((Land) this.target).getTrustedPlayers();
            case NATION:
                return (ArrayList<UUID>) ((Nation) this.target).getTrustedPlayers();
            case AREA:
                return (ArrayList<UUID>) ((Area) this.target).getTrustedPlayers();
            default:
                return null;
        }
    }

    public int getMembersAmount() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getMembersAmount();
            case NATION:
                return ((Nation) this.target).getLands().size();
            case AREA:
                return ((Area) this.target).getTrustedPlayers().size();
            default:
                return 0;
        }
    }
}
