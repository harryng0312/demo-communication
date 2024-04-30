package org.harryng.demo.api.asset.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.asset.validator.annotation.AssetAddValidated;
import org.harryng.demo.api.asset.validator.annotation.AssetDefaultValidated;
import org.harryng.demo.api.asset.validator.annotation.AssetEditValidated;
import org.harryng.demo.api.base.dto.AbstractStatedDto;
import org.harryng.demo.api.base.entity.BaseResourceModel;
import org.harryng.demo.api.base.validator.annotation.TreePathValidated;
import org.harryng.demo.api.base.validator.group.AddValGroup;
import org.harryng.demo.api.base.validator.group.EditValGroup;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
//@AssetDefaultValidated(groups = DefaultValGroup.class)
@AssetDefaultValidated
@AssetAddValidated(groups = AddValGroup.class)
@AssetEditValidated(groups = EditValGroup.class)
public class AssetDto extends AbstractStatedDto<Long> implements BaseResourceModel {
    private String name;
    private String description;
    private Long orgId;
    @TreePathValidated(groups = EditValGroup.class)
    private String orgTreepath;
}
