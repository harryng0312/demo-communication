package org.harryng.demo.impl.base.service;

import lombok.NonNull;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;
import org.harryng.demo.api.base.service.BaseAuthenticatedService;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractService<T extends BaseModel<Id>, Id extends Serializable> implements BaseAuthenticatedService<T, Id> {

    @Override
    public abstract BasePersistence<T, Id> getPersistence();

    @Override
    public @NonNull ResponseWrapper<T> getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra) {
//        Thread.sleep(Duration.ofMillis(1*1000));
//        System.out.println("EntityManager: " + getPersistence().getEntityManager().hashCode());
        final Optional<T> optional = getPersistence().findById(id);
        if(optional.isPresent()){
            return ResponseWrapper.<T>builder().data(optional.get()).build();
        }
        return ResponseWrapper.<T>builder().build();
    }

    @Override
    public @NonNull ResponseWrapper<T> add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception {
        final T savedObj = getPersistence().save(obj);
        return ResponseWrapper.<T>builder().data(savedObj).build();
    }

    @Override
    public @NonNull ResponseWrapper<T> edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception {
        final T savedObj = getPersistence().save(obj);
        return ResponseWrapper.<T>builder().data(savedObj).build();
    }

    @Override
    public @NonNull ResponseWrapper<Void> remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception {
        getPersistence().deleteById(id);
        return ResponseWrapper.<Void>builder().build();
    }
}
