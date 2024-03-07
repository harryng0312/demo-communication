package org.harryng.demo.user.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.pojo.dto.ResponseWrapper;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String screenName;
    private LocalDateTime dob;
}
