package org.harryng.demo.base.service;

import org.harryng.demo.base.persistence.BasePersistence;
import org.harryng.demo.base.pojo.data.model.BaseModel;

import java.io.Serializable;
public abstract class AbstractService<Id extends Serializable, T extends BaseModel<Id>> implements BaseService<Id, T> {

    @Override
    public abstract BasePersistence<Id, T> getPersistence();

    @Override
    public T getById(Id id) throws Exception {
//        Thread.sleep(Duration.ofMillis(1*1000));
        System.out.println("EntityManager: " + getPersistence().getEntityManager().hashCode());
        return getPersistence().selectById(id);
    }

    @Override
    public int add(T obj) throws Exception {
        return getPersistence().insert(obj);
    }

    @Override
    public int edit(T obj) throws Exception {
        return getPersistence().update(obj);
    }

    @Override
    public int remove(Id id) throws Exception {
        return getPersistence().delete(id);
    }
}
