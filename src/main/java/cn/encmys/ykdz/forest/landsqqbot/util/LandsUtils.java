package cn.encmys.ykdz.forest.landsqqbot.util;

import me.angeschossen.lands.api.land.Container;
import me.angeschossen.lands.api.land.LandArea;

import java.util.ArrayList;
import java.util.Collection;

public class LandsUtils {
    public static ArrayList<String> toNameList(Collection<? extends Container> containers) {
        ArrayList<String> result = new ArrayList<>();
        for(Container container : containers) {
            for(LandArea landArea : container.getAreas()) {
                result.add(landArea.getName());
            }
        }
        return result;
    }
}
