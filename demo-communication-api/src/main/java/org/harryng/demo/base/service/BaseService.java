package org.harryng.demo.base.service;

import org.harryng.demo.base.persistence.BasePersistence;
import org.harryng.demo.base.entity.BaseModel;

import java.io.Serializable;

public interface BaseService<T extends BaseModel<Id>, Id extends Serializable> {
    BasePersistence<T, Id> getPersistence();

    T getById(Id id) throws Exception;
    int add(T obj) throws Exception;
    int edit(T obj) throws Exception;
    int remove(Id id) throws Exception;
}
