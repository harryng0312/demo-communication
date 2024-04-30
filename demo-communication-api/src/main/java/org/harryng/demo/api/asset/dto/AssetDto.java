package org.harryng.demo.api.asset.dto;

import lombok.*;
import org.harryng.demo.api.asset.validator.AssetConstraint;
import org.harryng.demo.api.base.dto.AbstractStatedDto;
import org.harryng.demo.api.base.entity.BaseResourceModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AssetConstraint
public class AssetDto extends AbstractStatedDto<Long> implements BaseResourceModel {
    private String name;
    private String description;
    private Long orgId;
    private String orgTreepath;
}
