package org.harryng.demo.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AbstractModel<Idt extends Serializable> implements BaseModel<Idt> {
    @Id
    @Column(name = "id_", unique = true, nullable = false)
    private Idt id;
}
