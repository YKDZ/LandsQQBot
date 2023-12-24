package cn.encmys.ykdz.forest.landsqqbot.manager;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MessageConfigManager {
    private static Plugin plugin = LandsQQBot.getPlugin();
    private static YamlConfiguration config;
    private static ConfigurationSection argsSection;
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
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static String getMessage(String path) {
        return config.getString("messages." + path);
    }

    public static String getProperNoun(String noun) {
        return config.getString("proper-noun." + noun);
    }

    public static List<String> getCommandPrefix() {
        return commandPrefix;
    }

    public static ConfigurationSection getArgsSection() {
        return argsSection;
    }

    public static Map<String, Object> getCommandArgsMap() {
        return argsSection.getValues(false);
    }

    public static List<String> getArgsSeparators() {
        return config.getStringList("args-separators");
    }

    public static List<String> getKeyValueSeparators() {
        return config.getStringList("key-value-separators");
    }

}
