package cn.encmys.ykdz.forest.landsqqbot.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {
    public static String joinList(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s).append("\n");
        }
        return builder.toString();
    }

    public static List<String> parseVariables(List<String> list, HashMap<String, Object> map, int itemAmount) {
        List<String> result = new ArrayList<>();
        HashMap<String, Object> lineMap = new HashMap<>();

        for (String line : list) {
            if (line.contains("@list-item")) {
                for(int i = 0; i < itemAmount; i++) {
                    for(String var : extractVariables(line)) {
                        Object value = map.get(var);
                        if(value instanceof ArrayList) {
                            lineMap.put(var, ((ArrayList<Object>) value).get(i));
                        } else {
                            lineMap.put(var, value);
                        }
                    }
                    result.add(parseVariables(line, lineMap));
                }
            } else {
                result.add(parseVariables(line, map));
            }
        }

        return result;
    }

    public static List<String> parseVariables(List<String> list, HashMap<String, Object> map) {
        List<String> result = new ArrayList<>();
        for(String line : list) {
            result.add(parseVariables(line, map));
        }
        return result;
    }

    public static String parseVariables(String s, HashMap<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            s = s.replaceAll("\\{" + key + "\\}", String.valueOf(value));
        }
        return s;
    }

    public static ArrayList<String> extractVariables(String line) {
        ArrayList<String> variableKeys = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String variableKey = matcher.group(1);
            variableKeys.add(variableKey);
        }

        return variableKeys;
    }
}
