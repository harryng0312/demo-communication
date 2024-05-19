package org.harryng.demo.api.conversation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.harryng.demo.api.base.dto.AbstractDto;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.conversation.validator.MessageDefaultValidated;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MessageDefaultValidated
public abstract class AbstractMessage extends AbstractDto<String> {
    public static final int TYPE_MSG_TEXT = 1;
    public static final int TYPE_MSG_BIN = 2;
    public static final int TYPE_MSG_PART = 3;
    public static final int TYPE_MSG_SIGNAL = 4;
    public static final int TYPE_MSG_TEXT_REPLACE = 5;

    public static final int TYPE_RECIPIENT_IND = 1;
    public static final int TYPE_RECIPIENT_GROUP = 2;

    public static final int STATUS_WILL_SEND = 1;
    public static final int STATUS_IN_CREATING = 2;
    public static final int STATUS_IN_SENDING = 3;
    public static final int STATUS_SENT_ON = 4;
    public static final int STATUS_RECEIVED_ON = 5;
    public static final int STATUS_READ_ON = 6;

    @NotNull
    private String id = "";
    @NotNull
    private String senderId = "";
    @NotNull
    private String recipientId = "";
    private int type = TYPE_MSG_TEXT;
    private int recipientType = TYPE_RECIPIENT_IND;
    private Instant sendTime = Instant.MIN;
    private Instant receivedTime = Instant.MIN;
    private int status = STATUS_WILL_SEND;
}
