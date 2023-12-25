package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintBal;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintBasicInfo;
import cn.encmys.ykdz.forest.landsqqbot.action.PrintMembers;
import cn.encmys.ykdz.forest.landsqqbot.enums.QQCommandArgs;
import cn.encmys.ykdz.forest.landsqqbot.util.QQCommandUtils;
import me.angeschossen.lands.api.LandsIntegration;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiFriend;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

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
//
//            sender.sendMessage(args.toString());
//
//            if(args.get(QQCommandArgs.ACTION).equals("输出信息")) {
//                PrintBasicInfo action = null;
//                if(args.get(QQCommandArgs.LAND) != null) {
//                    action = new PrintBasicInfo(landAPI.getLandByName((String) args.get(QQCommandArgs.LAND)));
//                } else if(args.get(QQCommandArgs.NATION) != null) {
//                    action = new PrintBasicInfo(landAPI.getNationByName((String) args.get(QQCommandArgs.NATION)));
//                }
//                if (action != null) {
//                    sender.sendMessage(action.getResult());
//                }
//            } else if(args.get(QQCommandArgs.ACTION).equals("输出金库余额")) {
//                PrintBal action = null;
//                if(args.get(QQCommandArgs.LAND) != null) {
//                    action = new PrintBal(landAPI.getLandByName((String) args.get(QQCommandArgs.LAND)));
//                } else if(args.get(QQCommandArgs.NATION) != null) {
//                    action = new PrintBal(landAPI.getNationByName((String) args.get(QQCommandArgs.NATION)));
//                }
//                if (action != null) {
//                    sender.sendMessage(action.getResult());
//                }
//            } else if(args.get(QQCommandArgs.ACTION).equals("输出玩家成员")) {
//                PrintMembers action = null;
//                if(args.get(QQCommandArgs.LAND) != null) {
//                    action = new PrintMembers(landAPI.getLandByName((String) args.get(QQCommandArgs.LAND)));
//                } else if(args.get(QQCommandArgs.NATION) != null) {
//                    action = new PrintMembers(landAPI.getNationByName((String) args.get(QQCommandArgs.NATION)));
//                } else if(args.get(QQCommandArgs.AREA) != null) {
//                    action = new PrintMembers(landAPI.getNationByName((String) args.get(QQCommandArgs.AREA)));
//                }
//                if (action != null) {
//                    sender.sendMessage(action.getResult());
//                }
        }
    }
}
