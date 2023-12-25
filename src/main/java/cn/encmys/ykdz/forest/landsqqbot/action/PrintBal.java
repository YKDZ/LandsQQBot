package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.enums.ActionTargets;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.util.MessageUtils;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;

import java.util.HashMap;
import java.util.List;

public class PrintBal {
    private static final String messagePath = "print-bal";
    ActionTargets targetType;
    Object target;

    public PrintBal(Object target) {
        this.target = target;
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
            put("bal", getBal());
        }};

        return MessageUtils.joinList(MessageUtils.parseVariables(messages, args));
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

    public double getBal() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getBalance();
            case NATION:
                return ((Nation) this.target).getBalance();
            default:
                return 0;
        }
    }

}
