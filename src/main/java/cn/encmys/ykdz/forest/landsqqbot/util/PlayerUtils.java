package cn.encmys.ykdz.forest.landsqqbot.util;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class PlayerUtils {
    public static ArrayList<String> toNameList(Collection<UUID> uuids) {
        ArrayList<String> result = new ArrayList<>();
        for(UUID uuid : uuids) {
            result.add(Bukkit.getOfflinePlayer(uuid).getName());
        }
        return result;
    }
}
