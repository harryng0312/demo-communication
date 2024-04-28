package org.harryng.demo.impl.asset.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.asset.entity.AssetModel;
import org.harryng.demo.api.asset.validator.AssetConstraint;
import org.harryng.demo.api.asset.validator.AssetNameNotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "asset")
@AssetConstraint
public class AssetImpl extends AssetModel {

}
