package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LandsListener implements Listener {
    private final YamlConfiguration messageConfig = LandsQQBot.getMainConfig();

}
