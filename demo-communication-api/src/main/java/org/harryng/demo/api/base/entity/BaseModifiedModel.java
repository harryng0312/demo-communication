package org.harryng.demo.api.base.entity;

import java.time.LocalDateTime;

public interface BaseModifiedModel {
    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);
    LocalDateTime getModifiedDate();
    void setModifiedDate(LocalDateTime modifiedDate);
}
