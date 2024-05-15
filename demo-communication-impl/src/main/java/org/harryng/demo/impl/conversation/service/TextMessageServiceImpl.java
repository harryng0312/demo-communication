package org.harryng.demo.impl.conversation.service;

import jakarta.annotation.Resource;
import org.harryng.demo.api.conversation.dto.TextMessage;
import org.harryng.demo.api.conversation.service.TextMessageService;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationError;
import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.impl.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TextMessageServiceImpl implements TextMessageService {

    @Resource
    private CacheManager cacheManager;

    @Override
    public ValidationResult<String> sendMessage(SessionHolder sessionHolder, TextMessage message, Map<String, Object> extras) throws Exception {
        var result = new ValidationResult<String>();
        final var cache = cacheManager.<String, Object>getCache("message-cache");
        if (cache != null) {
            cache.put(message.getRecipientId(), message);
        } else {
            result.getValidationErrors().add(ValidationError.of("", "body", "cache is not available"));
        }
        return result;
    }
}
