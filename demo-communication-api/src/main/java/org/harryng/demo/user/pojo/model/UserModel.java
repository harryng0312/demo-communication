package org.harryng.demo.user.pojo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.pojo.entity.AbstractStatedEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public class UserModel extends AbstractStatedEntity<Long> {
    @Basic
    private String username;
    @Basic
    private String passwd;
    @Basic
    @Column(name = "screenname")
    private String screenName;
    @Basic
    private LocalDateTime dob;
    @Basic
    @Column(name = "passwd_enc_method")
    private String passwdEncryptedMethod;
}
