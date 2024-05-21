package org.harryng.demo.impl.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "usergroup_role")
public class UserGroupRoleImpl extends UserGroupRoleModel {
}
