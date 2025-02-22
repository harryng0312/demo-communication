package org.harryng.demo.impl.base.service;

import org.harryng.demo.impl.base.entity.BaseModel;

import java.io.Serializable;

public interface BaseSearchableValidatedService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable>
        extends BaseValidatedService<Ent, Dget, Dadd, Dedit, Id> {
//    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery, Map<String, Object> extra) throws Exception;
//    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, Class<T> entityClass, String queryStr, Map<String, Object> extra) throws Exception;
}
