package org.harryng.demo.base.pojo.entity;

import java.time.LocalDateTime;

public interface BaseStatedEntity {
    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);
    LocalDateTime getModifiedDate();
    void setModifiedDate(LocalDateTime modifiedDate);
    String getStatus();
    void setStatus(String status);
}
