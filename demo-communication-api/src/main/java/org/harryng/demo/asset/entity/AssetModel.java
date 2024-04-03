package org.harryng.demo.asset.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.entity.AbstractStatedModel;
import org.harryng.demo.base.entity.BaseResourceModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AssetModel extends AbstractStatedModel<String> implements BaseResourceModel {
    @Basic
    @Column(name = "name_")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "resource_id")
    private String resourceId;
    @Basic
    @Column(name = "resource_tree_path")
    private String resourceTreePath;
}
