package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.api.action.Action;
import cn.encmys.ykdz.forest.landsqqbot.api.action.PrintAction;
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

public class PrintBasicInfo implements PrintAction {
    private static final String messagePath = "print-basic-info";
    Target target;

    public PrintBasicInfo(Land land) {
        this.target = new LandTarget(land);
    }

    public PrintBasicInfo(Nation nation) {
        this.target = new NationTarget(nation);
    }

    public PrintBasicInfo(Area area) {
        this.target = new AreaTarget(area);
    }

    public String getResult() {
        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", target.getTypeName());
            put("name", target.getName());
            put("owner", target.getOwner());
            put("id", target.getId());
            put("size", target.getSize());
            put("member-amount", target.getMemberAmount());
            put("nation", target.getNation());
            put("capital", target.getCapital());
        }};
        return MessageUtils.joinList(MessageUtils.parseVariables(messages, args));
    }

}
