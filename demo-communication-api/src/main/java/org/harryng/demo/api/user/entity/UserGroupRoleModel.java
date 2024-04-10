package org.harryng.demo.api.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@MappedSuperclass
public class UserGroupRoleModel implements Serializable {
    @Id
    @Column(name = "usergroup_id")
    private Long usergroupId;
    @Id
    @Column(name = "role_id")
    private Long roleId;
}
