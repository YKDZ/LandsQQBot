package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintBasicInfo;
import cn.encmys.ykdz.forest.landsqqbot.enums.QQCommandArgs;
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
import java.util.Optional;

public class MiraiListener implements Listener {
    private final LandsIntegration landAPI = LandsQQBot.getLandAPI();
    private final MiraiBot bot = LandsQQBot.getBot();
    @EventHandler
    public void onFriendMessageReceive(MiraiFriendMessageEvent e) {

        if(bot == null) { return; }

        long senderId = e.getSenderID();
        String message = e.getMessage();
        MiraiFriend sender = bot.getFriend(senderId);

        if(QQCommandUtils.isQQCommand(message)) {
            HashMap<QQCommandArgs, Object> args = QQCommandUtils.parseQQCommand(message);

            sender.sendMessage(args.toString());

            if(args.get(QQCommandArgs.ACTION).equals("输出信息")) {
                PrintBasicInfo action = null;
                if(args.get(QQCommandArgs.LAND) != null) {
                    action = new PrintBasicInfo(landAPI.getLandByName((String) args.get(QQCommandArgs.LAND)));
                } else if(args.get(QQCommandArgs.AREA) != null) {
                    action = new PrintBasicInfo(landAPI.getLandByName((String) args.get(QQCommandArgs.AREA)));
                } else if(args.get(QQCommandArgs.NATION) != null) {
                    action = new PrintBasicInfo(landAPI.getLandByName((String) args.get(QQCommandArgs.NATION)));
                }
                if (action != null) {
                    sender.sendMessage(action.getResult());
                }
//                String landName = (String) args.get(QQCommandArgs.LAND);
//                Land land = landAPI.getLandByName(landName);
//
//                if(land == null) {
//                    sender.sendMessage("城镇 " + landName + " 不存在。");
//                    return;
//                }
//
//                builder.append("城镇 ").append(landName).append(" 的信息如下：").append("\n");
//                builder.append("- 城镇名：").append(landName).append("\n");
//                builder.append("- 城镇 ID：").append(land.getId()).append("\n");
//                builder.append("- 城镇从属的国家：").append(land.getNation() != null ? land.getNation().getName() : "没有加入任何国家").append("\n");
//                builder.append("- 城镇主人：").append(Bukkit.getOfflinePlayer(land.getOwnerUID()).getName()).append("\n");
//                builder.append("- 城镇最大区块数：").append(land.getMaxChunks()).append("\n");
//                builder.append("- 城镇最大成员数：").append(land.getMaxMembers()).append("\n");
            }
        }
    }
}
