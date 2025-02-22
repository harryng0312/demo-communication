package org.harryng.demo.impl.asset.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.base.entity.BaseResourceModel;
import org.harryng.demo.impl.base.validator.annotation.TreePathValidated;
import org.harryng.demo.impl.base.validator.group.EditValGroup;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetRes extends AssetReq implements BaseResourceModel {

    @NotNull
    @TreePathValidated(groups = EditValGroup.class)
    private String orgTreepath;
}
