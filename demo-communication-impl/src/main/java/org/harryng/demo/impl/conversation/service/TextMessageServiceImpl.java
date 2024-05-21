package org.harryng.demo.impl.conversation.service;

import lombok.RequiredArgsConstructor;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationError;
import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.impl.cache.CachesManager;
import org.harryng.demo.impl.conversation.dto.TextMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TextMessageServiceImpl implements TextMessageService {

    private final CachesManager cachesManager;

    @Override
    public ValidationResult<String> sendMessage(SessionHolder sessionHolder, TextMessage message, Map<String, Object> extras) throws Exception {
        var result = new ValidationResult<String>();
        final var cache = cachesManager.<String, Object>getCache("message-cache");
        if (cache != null) {
            cache.put(message.getRecipientId(), message);
        } else {
            result.getValidationErrors().add(ValidationError.of("", "body", "cache is not available"));
        }
        return result;
    }
}
