package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.util.StringUtils;
import me.dreamvoid.miraimc.MiraiMCConfig;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiFriend;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class MiraiListener implements Listener {
    public void onFriendMessageReceive(MiraiFriendMessageEvent e) {
        long senderId = e.getSenderID();
        String message = e.getMessage();
        MiraiBot bot = MiraiBot.getBot(e.getBotID());
        MiraiFriend sender = bot.getFriend(senderId);

        if(StringUtils.isQQCommand(message)) {
            HashMap<StringUtils.QQCommandArgs, Object> args = StringUtils.parseQQCommand(message);
            sender.sendMessage(args.get(StringUtils.QQCommandArgs.Action).toString());
            sender.sendMessage("你好");
        }
    }
}
