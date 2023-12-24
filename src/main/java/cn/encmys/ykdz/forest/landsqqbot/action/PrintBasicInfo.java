package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.enums.ActionTargets;
import cn.encmys.ykdz.forest.landsqqbot.manager.MainConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandArea;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.configuration.file.YamlConfiguration;

public class PrintBasicInfo {
    ActionTargets targetType;
    Object target;
    StringBuffer buffer = new StringBuffer(" ");
    private static final String messagePath = "print-basic-info";

    public PrintBasicInfo(Object target) {
        this.target = target;
        if(target instanceof Land) {
            this.targetType = ActionTargets.LAND;
        } else if(target instanceof Area) {
            this.targetType = ActionTargets.AREA;
        } else if(target instanceof Nation) {
            this.targetType = ActionTargets.NATION;
        } else {
            this.targetType = ActionTargets.NULL;
        }
    }

    public String getResult() {
        if(targetType == ActionTargets.NULL) {
            return "你查找的对象不存在";
        }

        buffer.append(MessageConfigManager.getMessage(messagePath + "header-prefix"))
                .append(getTargetTypeName())
                .append(getTargetName())
                .append(MessageConfigManager.getMessage(messagePath + "header-suffix"))
                .append("\n");

        buffer.append(MessageConfigManager.getMessage(messagePath + "item-prefix"))
                .append(getTargetTypeName())
                .append(" ")
                .append(MessageConfigManager.getProperNoun("id"))
                .append(getTargetId())
                .append(MessageConfigManager.getMessage(messagePath + "item-suffix"))
                .append("\n");

        return buffer.toString();
    }

    public String getTargetName() {
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

    public int getTargetId() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getId();
            case NATION:
                return ((Nation) this.target).getId();
            default:
                return 0;
        }
    }

    public String getTargetTypeName() {
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

}
