package org.harryng.demo.api.user.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.base.dto.AbstractStatedDto;
import org.harryng.demo.api.base.entity.BaseCodedModel;
import org.harryng.demo.api.base.entity.BaseHierarchyModel;
import org.harryng.demo.api.base.entity.BaseResourceModel;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends AbstractStatedDto<Long> implements BaseResourceModel {
    private String username;
    private String passwd;
    private String screenName;
    private LocalDate dob;
    private String passwdEncryptedMethod;
    private Long orgId;
    private String orgTreepath;
}
