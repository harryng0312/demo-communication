package org.harryng.demo.api.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "role_")
public class RoleImpl extends RoleModel {
//    @ElementCollection
//    @CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"))
//    @CollectionTable(name = "resourcepermission", joinColumns = @JoinColumn(name = "role_id"))
//    @Column(name = "id_")
    @Transient
    @Setter(AccessLevel.NONE)
    private List<ResourcePermissionImpl> permissions = new ArrayList<>();
}
