package org.harryng.demo.impl.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AuthenticationInfo implements Serializable {
    private String id = "";
    private String username = "";
    private LocalDateTime requestTime = LocalDateTime.now();
    private String token = "";
}