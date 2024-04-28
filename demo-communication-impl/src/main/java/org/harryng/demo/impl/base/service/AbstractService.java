package org.harryng.demo.impl.base.service;

import lombok.NonNull;
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
    public @NonNull Optional<T> getById(@NonNull SessionHolder sessionHolder, @NonNull Id id, Map<String, Object> extra) {
        return getPersistence().findById(id);
    }

    @Override
    public @NonNull T add(@NonNull SessionHolder sessionHolder, @NonNull T obj, Map<String, Object> extra) throws Exception {
        return getPersistence().save(obj);
    }

    @Override
    public @NonNull T edit(@NonNull SessionHolder sessionHolder, @NonNull T obj, Map<String, Object> extra) throws Exception {
        return getPersistence().save(obj);
    }

    @Override
    public void remove(@NonNull SessionHolder sessionHolder, @NonNull Id id, @NonNull Map<String, Object> extra) throws Exception {
        getPersistence().deleteById(id);
    }
}
