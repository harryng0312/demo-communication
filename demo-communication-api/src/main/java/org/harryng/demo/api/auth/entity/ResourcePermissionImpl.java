package org.harryng.demo.api.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "resourcepermission")
public class ResourcePermissionImpl extends ResourcePermissionModel {
//    @ElementCollection
//    @CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "permission_id"))
//    @CollectionTable(name = "role_", joinColumns = @JoinColumn(name = "permission_id"))
//    @Column(name = "role_id")
//    @Setter(AccessLevel.NONE)
//    private List<Long> roleId = new ArrayList<>();
//    private Long roleId = 0L;
}
