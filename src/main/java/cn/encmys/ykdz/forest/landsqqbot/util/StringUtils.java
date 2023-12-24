package cn.encmys.ykdz.forest.landsqqbot.util;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;

public class StringUtils {
    private final YamlConfiguration messageConfig = LandsQQBot.getMessageConfig();
    public static boolean isQQCommand(String message) {
        return message.charAt(0) == '$' && !message.contains("[图片]");
    }
    public static HashMap<String, Object> parseQQCommand(String message) {
        HashMap<String, Object> result = new HashMap<>();

        return result;
    }
}
