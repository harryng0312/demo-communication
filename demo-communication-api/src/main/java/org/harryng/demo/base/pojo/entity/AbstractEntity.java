package org.harryng.demo.base.pojo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AbstractEntity<Idt extends Serializable> implements BaseEntity<Idt> {
    @Id
    @Column(name = "id_", unique = true, nullable = false)
    private Idt id;
}
