package org.harryng.demo.impl.asset.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.asset.validator.AssetAddValidated;
import org.harryng.demo.impl.asset.validator.AssetDefaultValidated;
import org.harryng.demo.impl.asset.validator.AssetEditValidated;
import org.harryng.demo.impl.base.dto.AbstractStatedDto;
import org.harryng.demo.impl.base.entity.BaseResourceModel;
import org.harryng.demo.impl.base.validator.annotation.TreePathValidated;
import org.harryng.demo.impl.base.validator.group.AddValGroup;
import org.harryng.demo.impl.base.validator.group.EditValGroup;
import org.hibernate.validator.constraints.Range;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
//@AssetDefaultValidated(groups = DefaultValGroup.class)
//@NoArgsConstructor
@AssetDefaultValidated
@AssetAddValidated(groups = AddValGroup.class)
@AssetEditValidated(groups = EditValGroup.class)
public class AssetReq extends AbstractStatedDto<Long> {
    @NotNull(message = "{name.empty}")
    private String name;
    private String description;
    @NotNull
    @Range(min = 1L)
    private Long orgId;
}
