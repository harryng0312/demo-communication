package org.harryng.demo.api.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "user_usergroup")
public class UserUserGroupImpl extends UserUserGroupModel {
}
