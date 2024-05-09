package org.harryng.demo.api.asset.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.harryng.demo.api.asset.validator.AssetAddValidated;
import org.harryng.demo.api.asset.validator.AssetDefaultValidated;
import org.harryng.demo.api.asset.validator.AssetEditValidated;
import org.harryng.demo.api.base.dto.AbstractStatedDto;
import org.harryng.demo.api.base.entity.BaseResourceModel;
import org.harryng.demo.api.base.validator.annotation.TreePathValidated;
import org.harryng.demo.api.base.validator.group.AddValGroup;
import org.harryng.demo.api.base.validator.group.EditValGroup;
import org.hibernate.validator.constraints.Range;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
//@AssetDefaultValidated(groups = DefaultValGroup.class)
//@NoArgsConstructor
@AssetDefaultValidated
@AssetAddValidated(groups = AddValGroup.class)
@AssetEditValidated(groups = EditValGroup.class)
public class AssetDto extends AbstractStatedDto<Long> implements BaseResourceModel {
    @NotNull(message = "{name.empty}")
    private String name;
    private String description;
    @NotNull
    @Range(min = 1L)
    private Long orgId;
    @NotNull
    @TreePathValidated(groups = EditValGroup.class)
    private String orgTreepath;
}
