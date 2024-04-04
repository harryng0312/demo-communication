package org.harryng.demo.api.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "resourcepermission")
public class ResourcePermissionImpl extends ResourcePermissionModel {
//    @ElementCollection
//    @CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "permission_id"))
//    @Column(name = "role_id")
    @Setter(AccessLevel.NONE)
    private List<Long> roleId = new ArrayList<>();
}
