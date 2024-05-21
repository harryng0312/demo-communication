package org.harryng.demo.impl.base.entity;

import java.io.Serializable;

public interface BaseModel<Id extends Serializable> extends Serializable {
    Id getId();
    void setId(Id id);
}
