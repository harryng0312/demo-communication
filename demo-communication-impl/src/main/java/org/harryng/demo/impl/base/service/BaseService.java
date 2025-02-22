package org.harryng.demo.impl.base.service;

import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.impl.base.persistence.BasePersistence;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable> {
    BasePersistence<Ent, Id> getPersistence();

    Optional<Dget> getById(Id id) throws Exception;
    int add(Dadd obj) throws Exception;
    int edit(Dedit obj) throws Exception;
    int remove(Id id) throws Exception;
}
