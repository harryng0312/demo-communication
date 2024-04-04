package org.harryng.demo.impl.base.service;

import org.harryng.demo.api.base.persistence.BasePersistence;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseAuthenticatedService;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractService<T extends BaseModel<Id>, Id extends Serializable> implements BaseAuthenticatedService<T, Id> {

    @Override
    public abstract BasePersistence<T, Id> getPersistence();

    @Override
    public Optional<T> getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra) {
//        Thread.sleep(Duration.ofMillis(1*1000));
//        System.out.println("EntityManager: " + getPersistence().getEntityManager().hashCode());
        return getPersistence().findById(id);
    }

    @Override
    public T add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception {
        return getPersistence().save(obj);
    }

    @Override
    public T edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception {
        return getPersistence().save(obj);
    }

    @Override
    public void remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception {
        getPersistence().deleteById(id);
    }
}
