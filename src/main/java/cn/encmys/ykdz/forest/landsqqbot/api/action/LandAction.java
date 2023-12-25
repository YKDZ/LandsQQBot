package cn.encmys.ykdz.forest.landsqqbot.api.action;

import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandArea;

public abstract class LandAction extends Action {
    Land target;

    public LandAction(Land target) {
        this.target = target;
    }

    @Override
    public String getName() {
        return target.getName();
    }

    @Override
    public String getTypeName() {
        return MessageConfigManager.getArg("land");
    }

}
