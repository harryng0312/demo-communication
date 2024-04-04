package org.harryng.demo.auth.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.entity.AbstractModel;
import org.harryng.demo.base.entity.AbstractStatedModel;
import org.harryng.demo.base.entity.BaseStatusModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public class RoleModel extends AbstractModel<Long> implements BaseStatusModel {
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
    @Column(name = "status_")
    private int status;
}
