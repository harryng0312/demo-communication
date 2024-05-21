package org.harryng.demo.impl.organization.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "organization")
public class OrganizationImpl extends OrganizationModel {
//    @ElementCollection
//    @CollectionTable(name = "org_usergroup", joinColumns = @JoinColumn(name = "org_id"))
//    @Column(name = "usergroup_id")
//    @Setter(AccessLevel.NONE)
//    private List<Long> usergroupIds = new ArrayList<>();
}
