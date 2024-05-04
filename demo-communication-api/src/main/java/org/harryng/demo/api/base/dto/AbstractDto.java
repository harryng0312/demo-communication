package org.harryng.demo.api.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.harryng.demo.api.base.entity.BaseModel;

import java.io.Serializable;

@Data
public abstract class AbstractDto<Idt extends Serializable> implements BaseModel<Idt> {
    @NotNull(message = "{id.invalid}")
    private Idt id;
}
