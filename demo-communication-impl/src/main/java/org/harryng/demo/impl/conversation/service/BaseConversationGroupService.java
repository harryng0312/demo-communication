package org.harryng.demo.impl.conversation.service;

import org.harryng.demo.impl.conversation.dto.AbstractConversationGroup;
import org.harryng.demo.impl.conversation.dto.AbstractMessage;
import org.harryng.demo.api.util.SessionHolder;

import java.util.List;
import java.util.Map;

public interface BaseConversationGroupService<Group extends AbstractConversationGroup> {
    List<Group> getMessageByChatGroupIds(
            SessionHolder sessionHolder, List<String> groupIds, Map<String, Object> extras) throws Exception;

}
