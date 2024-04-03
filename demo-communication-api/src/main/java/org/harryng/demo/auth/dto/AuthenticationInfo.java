package org.harryng.demo.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "username",
        "password",
        "requestTime",
        "result"
})
@Data
public class AuthenticationInfo implements Serializable {
    private String id = null;
    private String username = null;
    private String password = null;
    private Date requestTime = null;
    private String result = null;
}
