package org.harryng.demo.base.service;

import org.harryng.demo.base.persistence.BasePersistence;
import org.harryng.demo.base.pojo.entity.BaseEntity;

import java.io.Serializable;

public interface BaseService<Id extends Serializable, T extends BaseEntity<Id>> {
    BasePersistence<Id, T> getPersistence();

    T getById(Id id) throws Exception;
    int add(T obj) throws Exception;
    int edit(T obj) throws Exception;
    int remove(Id id) throws Exception;
}
