package org.harryng.demo.impl.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.harryng.demo.impl.base.entity.BaseModel;

import java.io.Serializable;

@Data
public abstract class AbstractDto<Idt extends Serializable> implements BaseModel<Idt> {
    @NotNull
    private Idt id;
}
