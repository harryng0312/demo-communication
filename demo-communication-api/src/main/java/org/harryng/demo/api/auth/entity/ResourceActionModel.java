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
public abstract class ResourceActionModel extends AbstractModel<Long> {
    @Basic
    @Column(name = "resource_type")
    private String resourceType;
    @Basic
    @Column(name = "action_method")
    private String actionMethod;
    @Basic
    @Column(name = "action_bit")
    private long actionBit;
}
