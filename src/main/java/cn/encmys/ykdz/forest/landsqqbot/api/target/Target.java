package cn.encmys.ykdz.forest.landsqqbot.api.target;

import cn.encmys.ykdz.forest.landsqqbot.enums.MemberType;
import me.angeschossen.lands.api.inbox.InboxMessage;

import java.util.ArrayList;
import java.util.List;

public interface Target {
    String getName();
    String getTypeName();
    String getOwner();
    Object getSize();
    Object getBal();
    String getNation();
    int getMemberAmount();
    List<? extends InboxMessage> getInbox();
    ArrayList<String> getInboxMessages();
    Object getInboxAmount();
}
