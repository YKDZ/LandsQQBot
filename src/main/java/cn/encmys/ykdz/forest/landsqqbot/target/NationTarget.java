package cn.encmys.ykdz.forest.landsqqbot.target;

import cn.encmys.ykdz.forest.landsqqbot.api.target.Target;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class NationTarget implements Target {
    Nation nation;
    int requiredInboxMessageAmount;
    MemberType memberType;

    public NationTarget(Nation nation) {
        this.nation = nation;
    }

    public NationTarget(Nation nation, int requiredInboxMessageAmount) {
        this.nation = nation;
        this.requiredInboxMessageAmount = requiredInboxMessageAmount;
    }

    public NationTarget(Nation nation, MemberType memberType) {
        this.nation = nation;
        this.memberType = memberType;
    }

    @Override
    public String getTypeName() {
        return MessageConfigManager.getArg("nation");
    }

    @Override
    public String getName() {
        return nation.getName();
    }

    @Override
    public String getOwner() {
        return Bukkit.getOfflinePlayer(nation.getOwnerUID()).getName();
    }

    public Object getSize() {
        return nation.getChunksAmount();
    }

    @Override
    public Object getBal() {
        return nation.getBalance();
    }

    @Override
    public String getNation() {
        return MessageConfigManager.getMessagePlaceholder("none-nation");
    }

    @Override
    public int getMemberAmount() {
        switch (memberType) {
            case LAND:
                return nation.getLands().size();
            case AREA:
                return nation.getCapital().getContainers().size();
            case PLAYER:
                return nation.getTrustedPlayers().size();
        }
        return 0;
    }

    @Override
    public List<? extends InboxMessage> getInbox() {
        return null;
    }

    @Override
    public ArrayList<String> getInboxMessages() {
        return null;
    }

    @Override
    public Object getInboxAmount() {
        return null;
    }
}
