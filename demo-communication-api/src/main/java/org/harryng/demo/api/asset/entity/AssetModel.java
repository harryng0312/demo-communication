package org.harryng.demo.api.asset.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.asset.validator.AssetNameNotEmpty;
import org.harryng.demo.api.base.entity.AbstractStatedModel;
import org.harryng.demo.api.base.entity.BaseResourceModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AssetModel extends AbstractStatedModel<Long> implements BaseResourceModel {
    @Basic
    @Column(name = "name_")
//    @AssetNameNotEmpty
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "org_id")
//    @NotNull(message = "{asset.orgId.empty}")
    private Long orgId;
    @Basic
    @Column(name = "org_treepath")
    private String orgTreepath;
}
