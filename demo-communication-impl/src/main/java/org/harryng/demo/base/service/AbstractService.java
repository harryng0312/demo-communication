package org.harryng.demo.base.service;

import org.harryng.demo.base.persistence.BasePersistence;
import org.harryng.demo.base.pojo.data.model.BaseModel;
import org.harryng.demo.base.pojo.dto.SessionHolder;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractService<Id extends Serializable, T extends BaseModel<Id>> implements BaseAuthenticatedService<Id, T> {

    @Override
    public abstract BasePersistence<Id, T> getPersistence();

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
