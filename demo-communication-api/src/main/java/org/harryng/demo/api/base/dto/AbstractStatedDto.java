package org.harryng.demo.api.base.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.api.base.entity.BaseModifiedModel;
import org.harryng.demo.api.base.entity.BaseStatusModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AbstractStatedDto<Idt extends Serializable> extends AbstractDto<Idt> implements BaseModifiedModel, BaseStatusModel {
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int status;
}
