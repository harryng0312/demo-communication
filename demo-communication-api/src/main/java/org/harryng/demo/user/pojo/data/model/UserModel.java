package org.harryng.demo.user.pojo.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.base.pojo.data.model.AbstractStatedModel;

import java.time.LocalDateTime;

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
    private LocalDateTime dob;
    @Basic
    @Column(name = "passwd_enc_method")
    private String passwdEncryptedMethod;
}
