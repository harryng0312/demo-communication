package org.harryng.demo.api.conversation.service;

import org.harryng.demo.api.conversation.dto.AbstractConversationGroup;
import org.harryng.demo.api.conversation.dto.AbstractMessage;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;

import java.util.List;
import java.util.Map;

public interface AbstractConversationGroupService<Msg extends AbstractMessage> {
    List<AbstractConversationGroup> getMessageByChatGroupIds(
            SessionHolder sessionHolder, List<String> groupIds, Map<String, Object> extras) throws Exception;

}
