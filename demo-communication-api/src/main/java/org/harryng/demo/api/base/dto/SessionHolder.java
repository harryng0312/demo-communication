package org.harryng.demo.api.base.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class SessionHolder implements Serializable {
    public static final SessionHolder ANONYMOUS = SessionHolder.builder().build();
    @NonNull @Builder.Default
    private Long userId = 0L;
    @NonNull @Builder.Default
    private String username = "";
    @NonNull @Builder.Default
    private LocalDateTime validityAfter = LocalDateTime.MIN;
    @NonNull @Builder.Default
    private LocalDateTime validityBefore = LocalDateTime.MIN;
    @NonNull @Builder.Default
    private String sessionId = "";

    // permission section

}
