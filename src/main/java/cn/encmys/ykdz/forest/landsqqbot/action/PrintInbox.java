package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.api.target.Target;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.target.AreaTarget;
import cn.encmys.ykdz.forest.landsqqbot.target.LandTarget;
import cn.encmys.ykdz.forest.landsqqbot.target.NationTarget;
import cn.encmys.ykdz.forest.landsqqbot.util.MessageUtils;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;

import java.util.HashMap;
import java.util.List;

public class PrintInbox {
    private static final String messagePath = "print-inbox";
    private Target target;

    public PrintInbox(Land land, int requiredInboxMessageAmount) {
        this.target = new LandTarget(land, requiredInboxMessageAmount);
    }

    public PrintInbox(Nation nation, int requiredInboxMessageAmount) {
        this.target = new NationTarget(nation, requiredInboxMessageAmount);
    }

    public PrintInbox(Area area, int requiredInboxMessageAmount) {
        this.target = new AreaTarget(area, requiredInboxMessageAmount);
    }

    public String getResult() {

        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", target.getTypeName());
            put("name", target.getName());
            put("amount", target.getInboxMessageAmount());
            put("messages", target.getInboxMessages());
        }};

        return MessageUtils.joinList(MessageUtils.parseVariables(messages, args, (int) target.getInboxMessageAmount()));
    }

}
