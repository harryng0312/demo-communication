package org.harryng.demo.impl.base.service;

import org.harryng.demo.api.base.persistence.BasePersistence;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseAuthenticatedService;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractService<T extends BaseModel<Id>, Id extends Serializable> implements BaseAuthenticatedService<T, Id> {

    @Override
    public abstract BasePersistence<T, Id> getPersistence();

    @Override
    public T getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception {
//        Thread.sleep(Duration.ofMillis(1*1000));
//        System.out.println("EntityManager: " + getPersistence().getEntityManager().hashCode());
        return getPersistence().selectById(id);
    }

    @Override
    public int add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception {
        return getPersistence().insert(obj);
    }

    @Override
    public int edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception {
        return getPersistence().update(obj);
    }

    @Override
    public int remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception {
        return getPersistence().delete(id);
    }
}
