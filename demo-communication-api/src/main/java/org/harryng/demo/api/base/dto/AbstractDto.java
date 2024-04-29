package org.harryng.demo.api.base.dto;

import lombok.Data;
import org.harryng.demo.api.base.entity.BaseModel;

import java.io.Serializable;

@Data
public abstract class AbstractDto<Idt extends Serializable> implements BaseModel<Idt> {
    private Idt id;
}
