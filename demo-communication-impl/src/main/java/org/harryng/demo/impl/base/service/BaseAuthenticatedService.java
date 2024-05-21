package org.harryng.demo.impl.base.service;

import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.impl.base.persistence.BasePersistence;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public interface BaseAuthenticatedService<T extends BaseModel<Id>, Et extends BaseModel<Id>, Id extends Serializable> {
    BasePersistence<Et, Id> getPersistence();

    Optional<T> getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra);
    ValidationResult<T> add(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    ValidationResult<T> edit(SessionHolder sessionHolder, T obj, Map<String, Object> extra) throws Exception;
    ValidationResult<Id> remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
}
