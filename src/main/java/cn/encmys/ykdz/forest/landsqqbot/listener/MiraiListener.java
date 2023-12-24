package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.util.StringUtils;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class MiraiListener implements Listener {
    public void onFriendMessageReceive(MiraiFriendMessageEvent e) {
        long senderId = e.getSenderID();
        String message = e.getMessage();

        if(StringUtils.isQQCommand(message)) {
            HashMap<String, Object> args = StringUtils.parseQQCommand(message);

        }
    }
}
