package org.harryng.demo.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String screenName;
    private LocalDateTime dob;
}
