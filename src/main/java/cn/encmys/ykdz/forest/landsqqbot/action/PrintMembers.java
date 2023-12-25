package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.api.target.Target;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
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

public class PrintMembers {
    private static final String messagePath = "print-members";
    private Target target;

    public PrintMembers(Land land, MemberType memberType) {
        this.target = new LandTarget(land, memberType);
    }

    public PrintMembers(Nation nation, MemberType memberType) {
        this.target = new NationTarget(nation, memberType);
    }

    public PrintMembers(Area area, MemberType memberType) {
        this.target = new AreaTarget(area, memberType);
    }

    public String getResult() {
        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", target.getTypeName());
            put("name", target.getName());
            put("member-type", target.getMemberType());
            put("members", target.getMembers());
            put("members-amount", target.getMemberAmount());
        }};

        return MessageUtils.joinList(MessageUtils.parseVariables(messages, args, target.getMemberAmount()));
    }

}
