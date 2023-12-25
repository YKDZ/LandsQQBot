package cn.encmys.ykdz.forest.landsqqbot.target;

import cn.encmys.ykdz.forest.landsqqbot.api.target.Target;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LandTarget implements Target {
    Land land;
    int requiredInboxMessageAmount;
    MemberType memberType;

    public LandTarget(Land land) {
        this.land = land;
    }

    public LandTarget(Land land, int requiredInboxMessageAmount) {
        this.land = land;
        this.requiredInboxMessageAmount = requiredInboxMessageAmount;
    }

    public LandTarget(Land land, MemberType memberType) {
        this.land = land;
        this.memberType = memberType;
    }

    @Override
    public String getTypeName() {
        return MessageConfigManager.getArg("land");
    }

    @Override
    public String getName() {
        return land.getName();
    }

    @Override
    public String getOwner() {
        return Bukkit.getOfflinePlayer(land.getOwnerUID()).getName();
    }

    @Override
    public Object getSize() {
        return land.getChunksAmount();
    }

    @Override
    public Object getBal() {
        return land.getBalance();
    }

    @Override
    public int getMemberAmount() {
        switch (memberType) {
            case LAND:
                return 0;
            case AREA:
                return land.getContainers().size();
            case PLAYER:
                return land.getTrustedPlayers().size();
        }
        return 0;
    }

    @Override
    public List<? extends InboxMessage> getInbox() {
        return land.getInbox();
    }

    @Override
    public ArrayList<String> getInboxMessages() {
        ArrayList<String> messages = new ArrayList<>();
        List<? extends InboxMessage> inbox = getInbox();
        for(int i = 0; i < (int) getInboxAmount(); i++) {
            messages.add(inbox.get(i).getTextWithDate(null));
        }
        return messages;
    }

    @Override
    public Object getInboxAmount() {
        return Math.min(requiredInboxMessageAmount, getInbox().size());
    }

    @Override
    public String getNation() {
        return Optional.ofNullable(land.getNation())
                .map(Nation::getName)
                .orElse(MessageConfigManager.getMessagePlaceholder("none-nation"));
    }

}
