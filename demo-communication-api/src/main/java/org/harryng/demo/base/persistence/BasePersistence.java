package org.harryng.demo.base.persistence;

import jakarta.persistence.EntityManager;
import org.harryng.demo.base.pojo.entity.BaseEntity;

import java.io.Serializable;


public interface BasePersistence<Id extends Serializable, T extends BaseEntity<Id>> {
    EntityManager getEntityManager();
    EntityManager getEntityManager(String entityManagerName);
    Class<T> getEntityClass();

    T selectById(Id id) throws Exception;
    int insert(T obj) throws Exception;
    int update(T obj) throws Exception;
    int delete(Id id) throws Exception;
}
