package org.harryng.demo.api.conversation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.harryng.demo.api.base.dto.AbstractDto;
import org.harryng.demo.api.conversation.validator.ConversationGroupDefaultValidated;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ConversationGroupDefaultValidated
public class AbstractConversationGroup extends AbstractDto<String> {
    @NotNull
    private String displayName = "";
    @NotNull
    private Instant createdDate = Instant.MIN;
    @Setter(AccessLevel.NONE)
    private List<String> members = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private List<AbstractMessage> messages = new ArrayList<>();
}
