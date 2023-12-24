package cn.encmys.ykdz.forest.landsqqbot.util;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import cn.encmys.ykdz.forest.landsqqbot.enums.QQCommandArgs;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQCommandUtils {

    public static boolean isQQCommand(String message) {
        for (String prefix : MessageConfigManager.getCommandPrefix()) {
            if (message.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<QQCommandArgs, Object> parseQQCommand(String command) {
        Map<String, Object> argsMap = MessageConfigManager.getCommandArgsMap();
        String regexArgs = String.join("|", MessageConfigManager.getArgsSeparators());
        String regexKeyValue = String.join("|", MessageConfigManager.getKeyValueSeparators());
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

}
