package cn.encmys.ykdz.forest.landsqqbot.manager;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MessageConfigManager {
    private static Plugin plugin = LandsQQBot.getPlugin();
    private static YamlConfiguration config;
    private static ConfigurationSection argsSection;
    private static ConfigurationSection actionSection;
    private static List<String> commandPrefix;

    public static void load() {
        String fileName = MainConfigManager.getConfig().getString("lang");
        File file = new File(plugin.getDataFolder(), "lang/" + fileName);
        config = new YamlConfiguration();

        if(!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("lang/" + fileName, false);
        }

        try {
            config.load(file);

            commandPrefix = config.getStringList("command-prefix");
            argsSection = config.getConfigurationSection("args");
            actionSection = config.getConfigurationSection("command-actions");
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static List<String> getMessage(String path) {
        return config.getStringList("messages." + path);
    }

    public static String getErrorMessage(String path) { return config.getString("messages.error." + path); }

    public static List<String> getActionMessage(String action) {
        return config.getStringList("messages.actions." + action);
    }

    public static String getMessagePlaceholder(String path) {
        return config.getString("messages.placeholders." + path);
    }

    public static String getProperNoun(String noun) {
        return config.getString("args." + noun);
    }

    public static List<String> getCommandPrefix() {
        return commandPrefix;
    }

    public static ConfigurationSection getArgsSection() {
        return argsSection;
    }

    public static Map<String, Object> getArgsMap() {
        return argsSection.getValues(false);
    }

    public static List<String> getArgsSeparators() {
        return config.getStringList("args-separators");
    }

    public static List<String> getKeyValueSeparators() {
        return config.getStringList("key-value-separators");
    }

    public static Map<String, Object> getActions() {
        return new HashMap<String, Object>() {{
           putAll(getGlobalActions());
            putAll(getGroupActions());
            putAll(getFriendActions());
        }};
    }

    public static Map<String, Object> getPublicAction() {
        return new HashMap<String, Object>() {{
            putAll(getGlobalActions());
            putAll(getGroupActions());
        }};
    }

    public static Map<String, Object> getFriendActions() {
        return Objects.requireNonNull(actionSection.getConfigurationSection("friend")).getValues(false);
    }

    public static Map<String, Object> getGroupActions() {
        return Objects.requireNonNull(actionSection.getConfigurationSection("group")).getValues(false);
    }

    public static Map<String, Object> getGlobalActions() {
        return Objects.requireNonNull(actionSection.getConfigurationSection("global")).getValues(false);
    }

    public static String getArg(String key) {
        return (String) getArgsMap().get(key);
    }
}
