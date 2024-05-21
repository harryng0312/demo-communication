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
@Table(name = "user_")
public class UserImpl extends UserModel {
//    @ElementCollection
//    @CollectionTable(name = "user_usergroup", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "usergroup_id")
//    @Transient
//    @Setter(AccessLevel.NONE)
//    private List<Long> usergroupIds = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE)
    private List<UserGroupImpl> userGroups = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE)
    private List<RoleImpl> roles = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE)
    private List<ResourcePermissionImpl> permissions = new ArrayList<>();
}
