package org.harryng.demo.base.pojo.data.model;

import java.time.LocalDateTime;

public interface BaseStatedModel {
    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);
    LocalDateTime getModifiedDate();
    void setModifiedDate(LocalDateTime modifiedDate);
    String getStatus();
    void setStatus(String status);
}
