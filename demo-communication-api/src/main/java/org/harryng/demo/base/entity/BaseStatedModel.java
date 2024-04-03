package org.harryng.demo.base.entity;

import java.time.LocalDateTime;

public interface BaseStatedModel extends BaseStatusModel{
    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);
    LocalDateTime getModifiedDate();
    void setModifiedDate(LocalDateTime modifiedDate);
}
