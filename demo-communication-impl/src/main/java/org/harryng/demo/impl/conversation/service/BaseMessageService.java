package org.harryng.demo.impl.conversation.service;

import org.harryng.demo.impl.conversation.dto.AbstractMessage;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;

import java.util.Map;

public interface BaseMessageService<Msg extends AbstractMessage> {
    ValidationResult<String> sendMessage(SessionHolder sessionHolder, Msg message, Map<String, Object> extras) throws Exception;
}
