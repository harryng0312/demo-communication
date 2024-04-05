package org.harryng.demo.api.user.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.base.entity.AbstractStatedModel;
import org.harryng.demo.api.base.entity.BaseHierarchyModel;
import org.harryng.demo.api.base.entity.BaseResourceModel;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class UserModel extends AbstractStatedModel<Long> implements BaseResourceModel {
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
    @Basic
    @Column(name = "org_id")
    private Long orgId;
    @Basic
    @Column(name = "org_treepath")
    private String orgTreepath;
}
