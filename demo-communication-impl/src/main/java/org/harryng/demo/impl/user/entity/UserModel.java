package org.harryng.demo.impl.user.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.base.entity.BaseResourceModel;
import org.harryng.demo.impl.base.entity.AbstractStatedModel;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class UserModel extends AbstractStatedModel<Long> implements BaseResourceModel {
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "passwd")
    private String passwd;
    @Basic
    @Column(name = "screenname")
    private String screenName;
    @Basic
    @Column(name = "dob")
    private LocalDate dob;
    @Basic
    @Column(name = "passwd_enc_method")
    private String passwdEncryptedMethod;
    @Basic
    @Column(name = "org_id")
    private Long orgId;
    @Basic
    @Column(name = "org_treepath")
    private String orgTreepath;
}
