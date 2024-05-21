package org.harryng.demo.impl.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.harryng.demo.impl.auth.entity.ResourcePermissionImpl;
import org.harryng.demo.impl.auth.entity.RoleImpl;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "usergroup")
public class UserGroupImpl extends UserGroupModel {
//    @ElementCollection
//    @CollectionTable(name = "usergroup_role", joinColumns = @JoinColumn(name = "usergroup_id"))
//    @Column(name = "role_id")
//    @Transient
//    @Setter(AccessLevel.NONE)
//    private List<Long> roleIds = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE)
    private List<RoleImpl> roles = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE)
    private List<ResourcePermissionImpl> permissions = new ArrayList<>();
}
