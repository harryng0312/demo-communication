package org.harryng.demo.api.auth.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.base.entity.AbstractModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class ResourcePermissionModel extends AbstractModel<Long> {
    @Basic
    @Column(name = "resource_type")
    private String resourceType;
    @Basic
    @Column(name = "prim_key")
    private long primKey;
    @Basic
    @Column(name = "action_flag")
    private long actionFlag;
    @Basic
    @Column(name = "scope")
    private int scope;
}
