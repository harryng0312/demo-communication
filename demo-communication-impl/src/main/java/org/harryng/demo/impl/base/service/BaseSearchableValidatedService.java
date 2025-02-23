package org.harryng.demo.impl.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.entity.BaseModel;

import java.io.Serializable;
import java.util.Map;

public interface BaseSearchableValidatedService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable>
        extends BaseValidatedService<Ent, Dget, Dadd, Dedit, Id> {
    PageResult<Dget> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<Ent> criteriaQuery, Map<String, Object> extra) throws Exception;

    PageResult<Dget> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, Class<Ent> entityClass, String queryStr, Map<String, Object> extra) throws Exception;
}
