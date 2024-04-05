package org.harryng.demo.api.organization.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
