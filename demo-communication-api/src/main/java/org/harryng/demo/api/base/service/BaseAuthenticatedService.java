package org.harryng.demo.api.base.service;

import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;

import java.io.Serializable;
import java.util.Map;

public interface BaseAuthenticatedService<T extends BaseModel<Id>, Id extends Serializable> {
    BasePersistence<T, Id> getPersistence();

    T getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
    int add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    int edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    int remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
}
