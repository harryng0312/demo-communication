package org.harryng.demo.impl.conversation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.conversation.validator.MessageDefaultValidated;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MessageDefaultValidated
public class TextMessage extends AbstractMessage{
    @NotNull
    @Size(min = 1, max = 150)
    private String content = "";

    @NotNull
    private String replacedId = "";
}
