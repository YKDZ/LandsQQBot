package cn.encmys.ykdz.forest.landsqqbot.listener;

import cn.encmys.ykdz.forest.landsqqbot.LandsQQBot;
import cn.encmys.ykdz.forest.landsqqbot.api.action.Action;
import cn.encmys.ykdz.forest.landsqqbot.enums.Actions;
import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import cn.encmys.ykdz.forest.landsqqbot.enums.QQCommandArgs;
import cn.encmys.ykdz.forest.landsqqbot.factory.ActionFactory;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.util.MapUtils;
import cn.encmys.ykdz.forest.landsqqbot.util.QQCommandUtils;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiFriend;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class QQListener implements Listener {
    private final MiraiBot bot = LandsQQBot.getBot();
    @EventHandler
    public void onFriendMessageReceive(MiraiFriendMessageEvent e) {

        if(bot == null) { return; }

        long senderId = e.getSenderID();
        String message = e.getMessage();
        MiraiFriend sender = bot.getFriend(senderId);

        if(QQCommandUtils.isQQCommand(message)) {

            HashMap<QQCommandArgs, Object> args = QQCommandUtils.parseQQCommand(message);
            Actions actionKey = Actions.fromString(MapUtils.getKey(MessageConfigManager.getActions(), args.get(QQCommandArgs.ACTION)));
            Action action = null;

            sender.sendMessage(args.toString());

            if(actionKey == null) {
                sender.sendMessage(MessageConfigManager.getErrorMessage("action-invalid"));
                return;
            }

            switch (actionKey) {
                case PRINT_BAL:
                case PRINT_BASIC_INFO:
                    action = ActionFactory.build(actionKey, args.get(QQCommandArgs.LAND));
                    break;
                case PRINT_INBOX:
                    action = ActionFactory.build(actionKey, args.get(QQCommandArgs.LAND), (int) args.get(QQCommandArgs.AMOUNT));
                    break;
                case PRINT_MEMBERS:
                    action = ActionFactory.build(actionKey, args.get(QQCommandArgs.LAND), (MemberType) args.get(QQCommandArgs.MEMBER_TYPE));
                    break;
            }

            if (action != null) {
                sender.sendMessage(action.getResult());
            }
        }
    }
}
