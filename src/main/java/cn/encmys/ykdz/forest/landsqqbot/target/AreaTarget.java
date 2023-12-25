package cn.encmys.ykdz.forest.landsqqbot.target;

import cn.encmys.ykdz.forest.landsqqbot.api.target.Target;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AreaTarget implements Target {
    Area area;
    int requiredInboxMessageAmount;
    MemberType memberType;

    public AreaTarget(Area area) {
        this.area = area;
    }

    public AreaTarget(Area area, int requiredInboxMessageAmount) {
        this.area = area;
        this.requiredInboxMessageAmount = requiredInboxMessageAmount;
    }

    public AreaTarget(Area area, MemberType memberType) {
        this.area = area;
        this.memberType = memberType;
    }

    @Override
    public String getTypeName() {
        return MessageConfigManager.getArg("area");
    }

    @Override
    public String getName() {
        return area.getName();
    }

    @Override
    public String getOwner() {
        return Bukkit.getOfflinePlayer(area.getOwnerUID()).getName();
    }

    @Override
    public Object getSize() {
        return MessageConfigManager.getMessagePlaceholder("none-size");
    }

    @Override
    public Object getBal() {
        return MessageConfigManager.getMessagePlaceholder("none-balance");
    }

    @Override
    public int getMemberAmount() {
        switch (memberType) {
            case LAND:
            case AREA:
                return 0;
            case PLAYER:
                return area.getTrustedPlayers().size();
        }
        return 0;
    }

    @Override
    public List<? extends InboxMessage> getInbox() {
        return area.getLand().getInbox();
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
        return Optional.of(area.getLand())
                .map(Land::getNation)
                .map(Nation::getName)
                .orElse(MessageConfigManager.getMessagePlaceholder("none-nation"));
    }

}
