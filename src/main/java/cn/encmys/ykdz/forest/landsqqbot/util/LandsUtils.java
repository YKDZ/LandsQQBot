package cn.encmys.ykdz.forest.landsqqbot.util;

import me.angeschossen.lands.api.land.Land;

import java.util.ArrayList;
import java.util.Collection;

public class LandsUtils {
    public static ArrayList<String> toNameList(Collection<? extends Land> lands) {
        ArrayList<String> result = new ArrayList<>();
        for(Land land : lands) {
            result.add(land.getName());
        }
        return result;
    }
}
