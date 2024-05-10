package org.harryng.demo.impl.conversation.service;

import org.harryng.demo.api.conversation.dto.ChatGroup;
import org.harryng.demo.api.conversation.service.ChatGroupService;
import org.harryng.demo.api.util.SessionHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatGroupServiceImpl implements ChatGroupService {
    @Override
    public List<ChatGroup> getMessageByChatGroupIds(SessionHolder sessionHolder, List<String> groupIds, Map<String, Object> extras) throws Exception {
        return List.of();
    }
}
