package org.harryng.demo.api.base.service;

import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public interface BaseAuthenticatedService<T extends BaseModel<Id>, Et extends BaseModel<Id>, Id extends Serializable> {
    BasePersistence<Et, Id> getPersistence();

    Optional<T> getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra);
    T add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    T edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    void remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
}
