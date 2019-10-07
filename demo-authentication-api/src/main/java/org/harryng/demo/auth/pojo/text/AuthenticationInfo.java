package org.harryng.demo.auth.pojo.text;

import java.io.Serializable;
import java.util.Date;

public class AuthenticationInfo implements Serializable {
    private String id = null;
    private String username = null;
    private String password = null;
    private Date requestTime = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}
