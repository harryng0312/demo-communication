package org.harryng.demo.api.base.service;

import lombok.NonNull;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public interface BaseAuthenticatedService<T extends BaseModel<Id>, Id extends Serializable> {
    BasePersistence<T, Id> getPersistence();

    ResponseWrapper<T> getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra);
    ResponseWrapper<T> add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    ResponseWrapper<T> edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    ResponseWrapper<Void> remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
}
