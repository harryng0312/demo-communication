package org.harryng.demo.impl.organization.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.base.dto.AbstractStatedDto;
import org.harryng.demo.impl.base.entity.BaseCodedModel;
import org.harryng.demo.impl.base.entity.BaseHierarchyModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrganizationDto extends AbstractStatedDto<Long> implements BaseCodedModel, BaseHierarchyModel<Long> {
    private String name;
    private String code;
    private String description;
    private Long parentId;
    private String treepath;
}
