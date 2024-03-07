package org.harryng.demo.base.pojo.dto;

import lombok.ToString;

public interface ResponseCode {
    int SUCCESS = 0;
    int COMMON_ERROR = -1;
    int AUTH_CANNOT_FIND_USERNAME = 1001;
    int AUTH_CANNOT_PASSWD_NOT_MATCH = 1002;
    int AUTH_PASSWD_EXPIRED = 1003;
    int AUTH_USER_LOCKED_OUT = 1004;
}
