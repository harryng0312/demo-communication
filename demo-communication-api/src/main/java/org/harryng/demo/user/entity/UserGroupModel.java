package org.harryng.demo.user.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.entity.AbstractStatedModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public class UserGroupModel extends AbstractStatedModel<Long> {
    @Basic
    @Column(name = "name_")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "org_id")
    private String organizationId;
    @Basic
    @Column(name = "parent_id")
    private long parentId;
    @Basic
    @Column(name = "role_inherited")
    private boolean roleInherited;
}
