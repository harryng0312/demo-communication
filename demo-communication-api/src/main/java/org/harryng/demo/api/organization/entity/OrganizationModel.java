package org.harryng.demo.api.organization.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.base.entity.BaseCodedModel;
import org.harryng.demo.api.base.entity.BaseHierarchyModel;
import org.harryng.demo.api.base.entity.AbstractStatedModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class OrganizationModel extends AbstractStatedModel<Long> implements BaseCodedModel, BaseHierarchyModel<Long> {
    @Basic
    @Column(name = "name_")
    private String name;
    @Basic
    @Column(name = "code_")
    private String code;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "parent_id")
    private Long parentId;
    @Basic
    @Column(name = "treepath")
    private String treepath;
}
