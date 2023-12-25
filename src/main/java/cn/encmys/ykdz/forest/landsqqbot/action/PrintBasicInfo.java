package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.enums.ActionTargets;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.util.MessageUtils;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PrintBasicInfo {
    ActionTargets targetType;
    Object target;
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
            return MessageConfigManager.getErrorMessage("target-invalid");
        }

        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", getTypeName());
            put("name", getName());
            put("owner", getOwner());
            put("id", getId());
            put("size", getSize());
            put("member-amount", getMemberAmount());
            put("nation", getNation());
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

    public int getId() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getId();
            case NATION:
                return ((Nation) this.target).getId();
            default:
                return 0;
        }
    }

    public int getSize() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getChunksAmount();
            case NATION:
                return ((Nation) this.target).getChunksAmount();
            default:
                return 0;
        }
    }

    public int getMaxChunk() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getMaxChunks();
            default:
                return 0;
        }
    }

    public String getOwner() {
        switch (this.targetType) {
            case LAND:
                return Bukkit.getOfflinePlayer(((Land) this.target).getOwnerUID()).getName();
            case NATION:
                return Bukkit.getOfflinePlayer(((Nation) this.target).getOwnerUID()).getName();
            case AREA:
                return Bukkit.getOfflinePlayer(((Area) this.target).getOwnerUID()).getName();
            default:
                return null;
        }
    }

    public int getMemberAmount() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getMembersAmount();
            case NATION:
                return ((Nation) this.target).getMembersAmount();
            case AREA:
                return ((Area) this.target).getTrustedPlayers().size();
            default:
                return 0;
        }
    }

    public String getNation() {
        switch (this.targetType) {
            case LAND:
                return Optional.ofNullable(((Land) this.target).getNation())
                        .map(Nation::getName)
                        .orElse(MessageConfigManager.getMessagePlaceholder("none-nation"));
            case AREA:
                return Optional.of(((Area) this.target).getLand())
                        .map(Land::getNation)
                        .map(Nation::getName)
                        .orElse(MessageConfigManager.getMessagePlaceholder("none-nation"));
            default:
                return null;
        }
    }

}
