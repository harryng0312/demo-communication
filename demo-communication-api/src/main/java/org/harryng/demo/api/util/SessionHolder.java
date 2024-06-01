package org.harryng.demo.api.util;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

@ToString
@EqualsAndHashCode
@Getter
@Builder
public class SessionHolder implements Serializable {

    public static final SessionHolder ANONYMOUS = SessionHolder.builder().build();
    @Builder.Default
    private final Long userId = 0L;
    @Builder.Default
    private final String username = "";
    @Builder.Default
    private final Locale locale = Locale.ENGLISH;
    @Builder.Default
    private final LocalDateTime notBefore = LocalDateTime.now();
    @Builder.Default
    private final LocalDateTime validity = LocalDateTime.now();
    @Builder.Default
    private final String sessionId = "";

    // permission section

}
