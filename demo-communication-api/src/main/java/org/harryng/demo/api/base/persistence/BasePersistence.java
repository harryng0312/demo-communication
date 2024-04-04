package org.harryng.demo.api.base.persistence;

import jakarta.persistence.EntityManager;
import org.harryng.demo.api.base.entity.BaseModel;

import java.io.Serializable;


public interface BasePersistence<T extends BaseModel<Id>, Id extends Serializable> {
    EntityManager getEntityManager();
    EntityManager getEntityManager(String entityManagerName);
    Class<T> getEntityClass();

    T selectById(Id id) throws Exception;
    int insert(T obj) throws Exception;
    int update(T obj) throws Exception;
    int delete(Id id) throws Exception;
}

//public interface BasePersistence<T extends BaseModel<Id>, Id extends Serializable> extends JpaRepository<T, Id> {

//}
