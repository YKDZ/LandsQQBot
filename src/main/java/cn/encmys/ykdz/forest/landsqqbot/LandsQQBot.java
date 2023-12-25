package cn.encmys.ykdz.forest.landsqqbot;

import cn.encmys.ykdz.forest.landsqqbot.listener.LandsListener;
import cn.encmys.ykdz.forest.landsqqbot.listener.MiraiListener;
import cn.encmys.ykdz.forest.landsqqbot.manager.MainConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import me.angeschossen.lands.api.LandsIntegration;
import me.dreamvoid.miraimc.api.MiraiBot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LandsQQBot extends JavaPlugin {
    private static LandsQQBot plugin;
    private static LandsIntegration landAPI = null;
    private static MiraiBot bot;

    @Override
    public void onEnable() {
        plugin = this;
        landAPI = LandsIntegration.of(plugin);

        MainConfigManager.load();
        MessageConfigManager.load();

        bot = MiraiBot.getBot(MainConfigManager.getConfig().getLong("bot.account"));

        Bukkit.getPluginManager().registerEvents(new LandsListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new MiraiListener(), plugin);
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static LandsIntegration getLandAPI() {
        return landAPI;
    }

    public static MiraiBot getBot() {
        return bot;
    }

}
