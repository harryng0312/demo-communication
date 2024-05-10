package org.harryng.demo.impl.conversation.service;

import org.harryng.demo.api.conversation.dto.TextMessage;
import org.harryng.demo.api.conversation.service.TextMessageService;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TextMessageServiceImpl implements TextMessageService {
    @Override
    public ValidationResult<String> sendMessage(SessionHolder sessionHolder, TextMessage message, Map<String, Object> extras) throws Exception {
        final ValidationResult<String> result = new ValidationResult();
        return result;
    }
}
