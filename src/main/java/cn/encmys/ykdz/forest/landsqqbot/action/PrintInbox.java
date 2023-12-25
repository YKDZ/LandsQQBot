package cn.encmys.ykdz.forest.landsqqbot.action;

import cn.encmys.ykdz.forest.landsqqbot.enums.ActionTargets;
import cn.encmys.ykdz.forest.landsqqbot.manager.MessageConfigManager;
import cn.encmys.ykdz.forest.landsqqbot.util.MessageUtils;
import me.angeschossen.lands.api.inbox.InboxMessage;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.nation.Nation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrintInbox {
    private static final String messagePath = "print-inbox";
    private int amount;
    private ActionTargets targetType;
    private Object target;

    public PrintInbox(Object target, int amount) {
        this.target = target;
        this.amount = amount;
        if (target instanceof Land) {
            this.targetType = ActionTargets.LAND;
        } else if (target instanceof Area) {
            this.targetType = ActionTargets.AREA;
        } else if (target instanceof Nation) {
            this.targetType = ActionTargets.NATION;
        } else {
            this.targetType = ActionTargets.NULL;
        }
    }

    public String getResult() {
        if (targetType == ActionTargets.NULL) {
            return MessageConfigManager.getErrorMessage("target-invalid");
        }

        List<String> messages = MessageConfigManager.getActionMessage(messagePath);
        HashMap<String, Object> args = new HashMap<String, Object>() {{
            put("target", getTypeName());
            put("name", getName());
            put("amount", getAmount());
            put("messages", getMessages());
        }};

        return MessageUtils.joinList(MessageUtils.parseVariables(messages, args, getAmount()));
    }

    public String getTypeName() {
        switch (this.targetType) {
            case LAND:
                return MessageConfigManager.getProperNoun("land");
            case NATION:
                return MessageConfigManager.getProperNoun("nation");
            case AREA:
                return MessageConfigManager.getProperNoun("area");
            default:
                return null;
        }
    }

    public String getName() {
        switch (this.targetType) {
            case LAND:
                return ((Land) this.target).getName();
            case NATION:
                return ((Nation) this.target).getName();
            case AREA:
                return ((Area) this.target).getName();
            default:
                return null;
        }
    }

    public ArrayList<String> getMessages() {
        ArrayList<String> messages = new ArrayList<>();
        List<InboxMessage> inbox = null;
        switch (this.targetType) {
            case LAND:
                inbox = (List<InboxMessage>) ((Land) this.target).getInbox();
                break;
            case NATION:
                inbox = (List<InboxMessage>) ((Land) this.target).getInbox();
                break;
            case AREA:
                inbox = (List<InboxMessage>) ((Land) this.target).getInbox();
                break;
            default:
                inbox = null;
                break;
        }
        if (inbox != null) {
            for(int i = 0; i < getAmount(); i++) {
                messages.add(inbox.get(i).getTextWithDate(null));
            }
        }
        return messages;
    }

    public int getAmount() {
        int has;
        switch (this.targetType) {
            case LAND:
                has = ((Land) this.target).getInbox().size();
                break;
            case NATION:
                has = ((Nation) this.target).getInbox().size();
                break;
            case AREA:
                has = ((Area) this.target).getLand().getInbox().size();
                break;
            default:
                has = 0;
                break;
        }
        return Math.min(has, amount);
    }

}
