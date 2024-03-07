package org.harryng.demo.user.pojo.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.user.pojo.data.model.UserModel;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "auth_user")
public class UserImpl extends UserModel {

}
