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
    @ElementCollection
    @CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission_id")
    @Setter(AccessLevel.NONE)
    private List<Long> permissionIds = new ArrayList<>();
}
