package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import cn.encmys.ykdz.forest.landsqqbot.util.QQCommandUtils;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiFriend;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Objects;

public class MiraiListener implements Listener {
    private final LandsIntegration landAPI = LandsQQBot.getLandAPI();
    @EventHandler
    public void onFriendMessageReceive(MiraiFriendMessageEvent e) {
        long senderId = e.getSenderID();
        String message = e.getMessage();
        MiraiBot bot = MiraiBot.getBot(e.getBotID());
        MiraiFriend sender = bot.getFriend(senderId);

        if(QQCommandUtils.isQQCommand(message)) {
            StringBuilder messageBuilder = new StringBuilder();
            HashMap<QQCommandUtils.QQCommandArgs, Object> args = QQCommandUtils.parseQQCommand(message);

            sender.sendMessage(args.toString());

            if(args.get(QQCommandUtils.QQCommandArgs.Action) == "输出信息") {
                String landName = (String) args.get(QQCommandUtils.QQCommandArgs.Land);
                Land land = landAPI.getLandByName(landName);

                if(land == null) {
                    sender.sendMessage("城镇 " + landName + " 不存在。");
                    return;
                }

                messageBuilder.append("城镇 ").append(landName).append(" 的信息如下：").append("\n");
                messageBuilder.append("- 城镇名：").append(landName).append("\n");
                messageBuilder.append("- 城镇 ID：").append(land.getId()).append("\n");
                messageBuilder.append("- 城镇从属的国家：").append(land.getNation()).append("\n");
                messageBuilder.append("- 城镇主人：").append(Objects.requireNonNull(Bukkit.getPlayer(land.getOwnerUID())).getDisplayName()).append("\n");
                messageBuilder.append("- 城镇最大区块数：").append(land.getMaxChunks()).append("\n");
                messageBuilder.append("- 城镇最大成员数：").append(land.getMaxMembers()).append("\n");
            }

            sender.sendMessage(messageBuilder.toString());
        }
    }
}
