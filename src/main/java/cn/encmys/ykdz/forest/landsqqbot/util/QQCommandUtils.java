package cn.encmys.ykdz.forest.landsqqbot.util;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQCommandUtils {
    private static final YamlConfiguration messageConfig = LandsQQBot.getMessageConfig();
    private static final ConfigurationSection argsSection = messageConfig.getConfigurationSection("args");
    private static final List<String> commandIdentifiers = messageConfig.getStringList("command-identifier");

    public static boolean isQQCommand(String message) {
        return commandIdentifiers.contains(Character.toString(message.charAt(0))) && !message.contains("[图片]");
    }

    public static HashMap<QQCommandArgs, Object> parseQQCommand(String command) {
        Map<String, Object> argsMap = argsSection.getValues(false);
        String regexArgs = String.join("|", messageConfig.getStringList("args-separators"));
        String regexKeyValue = String.join("|", messageConfig.getStringList("key-value-separators"));
        String[] args = command.substring(1).split(regexArgs);

        HashMap<QQCommandArgs, Object> result = new HashMap<>();
        for(String arg : args) {
            String[] keyValue = arg.split(regexKeyValue);
            if(arg.split(regexKeyValue).length != 2) { continue; }

            String key = keyValue[0];
            String value = keyValue[1];

            if(!argsMap.containsValue(key)) { continue; }

            QQCommandArgs argId = QQCommandArgs.fromString(getKey(argsMap, key));

            if(argId == null) { continue; }

            result.put(argId, value);
        }

        return result;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public enum QQCommandArgs {
        Land,
        Player,
        Area,
        Nation,
        Role,
        Flag,
        Action;

        public static QQCommandArgs fromString(String name) {
            for (QQCommandArgs c : QQCommandArgs.values()) {
                if (c.name().equalsIgnoreCase(name)) {
                    return c;
                }
            }
            return null;
        }
    }
}
