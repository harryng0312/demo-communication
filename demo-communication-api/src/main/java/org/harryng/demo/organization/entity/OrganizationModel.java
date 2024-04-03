package org.harryng.demo.organization.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.entity.AbstractStatedModel;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class OrganizationModel extends AbstractStatedModel<String> {
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
    private String parentId;
    @Basic
    @Column(name = "tree_path")
    private String treePath;
}
