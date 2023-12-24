package cn.encmys.ykdz.forest.landsqqbot;

import cn.encmys.ykdz.forest.landsqqbot.listener.LandsListener;
import cn.encmys.ykdz.forest.landsqqbot.listener.MiraiListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class LandsQQBot extends JavaPlugin {

    private static LandsQQBot plugin;
    private static YamlConfiguration config;
    private static YamlConfiguration messageConfig;

    @Override
    public void onEnable() {
        loadMainConfig();
        loadMessageConfig();

        plugin = this;

        Bukkit.getPluginManager().registerEvents(new LandsListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new MiraiListener(), plugin);
    }

    @Override
    public void onDisable() {
    }

    public static YamlConfiguration getMainConfig() {
        return config;
    }

    public static YamlConfiguration getMessageConfig() {
        return messageConfig;
    }

    public void loadMainConfig() {

        File file = new File(getDataFolder(), "config.yml");
        config = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }

    public void loadMessageConfig() {
        String fileName = config.getString("lang");
        File file = new File(getDataFolder(), "lang/" + fileName);
        messageConfig = new YamlConfiguration();

        if(!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource("lang/" + fileName, false);
        }

        try {
            messageConfig.load(file);
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }

}
