package org.harryng.demo.base.pojo.data.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AbstractStatedModel<Id extends Serializable> extends AbstractModel<Id> implements BaseStatedModel {

    @Basic
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Basic
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    @Basic
    @Column(name = "status")
    private String status;

}
