package org.harryng.demo.api.util;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
@Builder
public class SessionHolder implements Serializable {

    public static final SessionHolder ANONYMOUS = SessionHolder.builder().build();
    @NonNull @Builder.Default
    private Long userId = 0L;
    @NonNull @Builder.Default
    private String username = "";
    @NonNull @Builder.Default
    private Locale locale = Locale.ENGLISH;
    @NonNull @Builder.Default
    private LocalDateTime notBefore = LocalDateTime.now();
    @NonNull @Builder.Default
    private LocalDateTime validity = LocalDateTime.now();
    @NonNull @Builder.Default
    private String sessionId = "";

    // permission section

}
