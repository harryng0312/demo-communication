package org.harryng.demo.impl.base.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.harryng.demo.impl.base.entity.BaseModifiedModel;
import org.harryng.demo.impl.base.entity.BaseStatusModel;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AbstractStatedDto<Idt extends Serializable> extends AbstractDto<Idt> implements BaseStatusModel {
//    @NotNull
//    private LocalDateTime createdDate;
//    @NotNull
//    private LocalDateTime modifiedDate;
    @Range(min = 0, max = 100)
    private int status;
}
