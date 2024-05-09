package org.harryng.demo.api.conversation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.conversation.validator.MessageDefaultValidated;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@MessageDefaultValidated
public class TextMessage extends AbstractMessage{
    @NotNull
    @Size(min = 1, max = 150)
    @Builder.Default
    private String content = "";

    @NotNull
    @Builder.Default
    private String originalMessageId = "";

    @NotNull
    @Builder.Default
    private String replacedMessageId = "";
}
