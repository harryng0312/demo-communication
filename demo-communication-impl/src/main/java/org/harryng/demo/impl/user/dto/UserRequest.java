package org.harryng.demo.impl.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.base.dto.AbstractRequest;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRequest extends AbstractRequest implements Serializable {
    private String username;
    private String screenName;
    private LocalDateTime dob;
}
