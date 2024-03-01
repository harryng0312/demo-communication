package org.harryng.demo.base.pojo.entity;

import java.io.Serializable;

public interface BaseEntity<Id extends Serializable> extends Serializable {
    Id getId();
    void setId(Id id);
}
