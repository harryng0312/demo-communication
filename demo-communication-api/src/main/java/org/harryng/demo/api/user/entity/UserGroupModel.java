package org.harryng.demo.api.user.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.base.entity.AbstractStatedModel;
import org.harryng.demo.api.base.entity.BaseHierarchyModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public class UserGroupModel extends AbstractStatedModel<Long> implements BaseHierarchyModel<Long> {
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
    private Long parentId;
    @Basic
    @Column(name = "treepath")
    private String treepath;
    @Basic
    @Column(name = "role_inherited")
    private boolean roleInherited;
}
