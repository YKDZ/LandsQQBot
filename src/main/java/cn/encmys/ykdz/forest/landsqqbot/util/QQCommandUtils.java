package cn.encmys.ykdz.forest.landsqqbot.util;

import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.enums.QQCommandArgs;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;

import java.util.HashMap;
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
        Map<String, Object> argsMap = MessageConfigManager.getArgsMap();
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

            QQCommandArgs argId = QQCommandArgs.fromString(MapUtils.getKey(argsMap, key));

            if(argId == null) { continue; }

            MemberType memberType = toMemberType(value);
            result.put(argId, memberType == null ? value : memberType);
        }

        return result;
    }

    public static MemberType toMemberType(String value) {
        String land = MessageConfigManager.getArg("land");
        String area = MessageConfigManager.getArg("area");
        String player = MessageConfigManager.getArg("player");
        String ally = MessageConfigManager.getArg("ally");
        if(land.equals(value)) {
            return MemberType.LAND;
        } else if(area.equals(value)) {
            return MemberType.AREA;
        } else if(player.equals(value)) {
            return MemberType.PLAYER;
        } else if(ally.equals(value)) {
            return MemberType.ALLY;
        }
        return null;
    }

}
