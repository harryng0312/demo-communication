package org.harryng.demo.api.asset.dto;

import lombok.*;
import org.harryng.demo.api.asset.validator.AssetAddGroup;
import org.harryng.demo.api.asset.validator.AssetEditGroup;
import org.harryng.demo.api.asset.validator.annotation.AssetAddValidated;
import org.harryng.demo.api.asset.validator.annotation.AssetEditValidated;
import org.harryng.demo.api.asset.validator.annotation.AssetValidated;
import org.harryng.demo.api.base.dto.AbstractStatedDto;
import org.harryng.demo.api.base.entity.BaseResourceModel;
import org.harryng.demo.api.base.validator.TreePathValidated;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AssetValidated
@AssetAddValidated(groups = AssetAddGroup.class)
@AssetEditValidated(groups = AssetEditGroup.class)
public class AssetDto extends AbstractStatedDto<Long> implements BaseResourceModel {
    private String name;
    private String description;
    private Long orgId;
    @TreePathValidated
    private String orgTreepath;
}
