package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.enums.ActionTargets;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.util.LandsUtils;
import cn.encmys.ykdz.forest.landsqqbot.util.MessageUtils;
import cn.encmys.ykdz.forest.landsqqbot.util.PlayerUtils;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.spi.LocaleNameProvider;

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
            return MessageConfigManager.getErrorMessage("target-invalid");
        }

        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", getTypeName());
            put("name", getName());
            put("members", getMembers());
            put("members-amount", getMembersAmount());
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

    public ArrayList<String> getMembers() {
        switch (this.memberType) {
            case PLAYER:
                switch (this.targetType) {
                    case LAND:
                        return PlayerUtils.toNameList(((Land) this.target).getTrustedPlayers());
                    case NATION:
                        return PlayerUtils.toNameList(((Nation) this.target).getTrustedPlayers());
                    case AREA:
                        return PlayerUtils.toNameList(((Area) this.target).getTrustedPlayers());
                    default:
                        return null;
                }
            case LAND:
                if (Objects.requireNonNull(this.targetType) == ActionTargets.NATION) {
                    return LandsUtils.toNameList(((Nation) this.target).getLands());
                }
        }
        return null;
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
