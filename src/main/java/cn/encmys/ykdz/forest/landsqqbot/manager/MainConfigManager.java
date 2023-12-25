package cn.encmys.ykdz.forest.landsqqbot.manager;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MainConfigManager {

    private static Plugin plugin = LandsQQBot.getPlugin();
    private static YamlConfiguration config;

    public static void load() {

        File file = new File(plugin.getDataFolder(), "config.yml");
        config = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
