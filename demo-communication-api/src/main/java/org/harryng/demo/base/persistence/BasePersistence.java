package org.harryng.demo.base.persistence;

import org.harryng.demo.base.pojo.data.model.BaseModel;
import org.hibernate.StatelessSession;

import java.io.Serializable;


public interface BasePersistence<Id extends Serializable, T extends BaseModel<Id>> {
    StatelessSession getStatelessSession();
    StatelessSession getStatelessSession(String entityManagerName);
    Class<T> getEntityClass();

    T selectById(Id id) throws Exception;
    int insert(T obj) throws Exception;
    int update(T obj) throws Exception;
    int delete(Id id) throws Exception;
}
