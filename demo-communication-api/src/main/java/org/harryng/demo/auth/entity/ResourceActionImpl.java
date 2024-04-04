package org.harryng.demo.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.organization.entity.OrganizationModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "resourceaction")
public class ResourceActionImpl extends ResourceActionModel {

}
