package org.harryng.demo.impl.base.service;

import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.impl.base.persistence.BasePersistence;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public interface BaseValidatedService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable> {
    BasePersistence<Ent, Id> getPersistence();

    Optional<Dget> getById(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
    ValidationResult<Dget> add(SessionHolder sessionHolder, Dadd obj, Map<String, Object> extra) throws Exception;
    ValidationResult<Dget> edit(SessionHolder sessionHolder, Dedit obj, Map<String, Object> extra) throws Exception;
    ValidationResult<Id> remove(SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
}
