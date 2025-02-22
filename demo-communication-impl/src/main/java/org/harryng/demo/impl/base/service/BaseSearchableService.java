package org.harryng.demo.impl.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;

import java.io.Serializable;

public interface BaseSearchableService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable> extends BaseService<Ent, Dget, Dadd, Dedit, Id> {
    PageResult<Ent> findByConditions(PageInfo pageInfo, CriteriaQuery<Ent> criteriaQuery) throws Exception;
    PageResult<Ent> findByConditions(PageInfo pageInfo, Class<Ent> entityClass, String queryStr) throws Exception;
}
