package org.harryng.demo.user.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.entity.AbstractStatedModel;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class UserModel extends AbstractStatedModel<Long> {
    @Basic
    private String username;
    @Basic
    private String passwd;
    @Basic
    @Column(name = "screenname")
    private String screenName;
    @Basic
    private LocalDate dob;
    @Basic
    @Column(name = "passwd_enc_method")
    private String passwdEncryptedMethod;
}
