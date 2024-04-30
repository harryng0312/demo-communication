package org.harryng.demo.api.asset.dto;

import lombok.*;
import org.harryng.demo.api.base.validator.group.AddValGroup;
import org.harryng.demo.api.base.validator.group.DefaultValGroup;
import org.harryng.demo.api.base.validator.group.EditValGroup;
import org.harryng.demo.api.asset.validator.annotation.AssetAddValidated;
import org.harryng.demo.api.asset.validator.annotation.AssetEditValidated;
import org.harryng.demo.api.asset.validator.annotation.AssetDefaultValidated;
import org.harryng.demo.api.base.dto.AbstractStatedDto;
import org.harryng.demo.api.base.entity.BaseResourceModel;
import org.harryng.demo.api.base.validator.annotation.TreePathValidated;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AssetDefaultValidated(groups = DefaultValGroup.class)
@AssetAddValidated(groups = AddValGroup.class)
@AssetEditValidated(groups = EditValGroup.class)
public class AssetDto extends AbstractStatedDto<Long> implements BaseResourceModel {
    private String name;
    private String description;
    private Long orgId;
    @TreePathValidated
    private String orgTreepath;
}
