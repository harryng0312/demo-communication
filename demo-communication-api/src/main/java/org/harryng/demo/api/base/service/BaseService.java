package org.harryng.demo.api.base.service;

import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<T extends BaseModel<Id>, Id extends Serializable> {
    BasePersistence<T, Id> getPersistence();

    Optional<T> getById(Id id) throws Exception;
    int add(T obj) throws Exception;
    int edit(T obj) throws Exception;
    int remove(Id id) throws Exception;
}
