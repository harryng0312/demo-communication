package org.harryng.demo.base.persistence;

import jakarta.persistence.EntityManager;
import org.harryng.demo.base.pojo.entity.BaseEntity;

import java.io.Serializable;


public interface BasePersistence<Id extends Serializable, T extends BaseEntity<Id>> {
    EntityManager getEntityManager();
    EntityManager getEntityManager(String entityManagerName);
    Class<T> getEntityClass();

    T selectById(Id id) throws RuntimeException, Exception;
    int insert(T obj) throws RuntimeException, Exception;
    int update(T obj) throws RuntimeException, Exception;
    int delete(Id id) throws RuntimeException, Exception;
}
