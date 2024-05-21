package org.harryng.demo.impl.base.service;

import org.harryng.demo.impl.base.entity.BaseModel;

import java.io.Serializable;

public interface BaseSearchableAuthenticatedService<T extends BaseModel<Id>, Et extends BaseModel<Id>, Id extends Serializable>
        extends BaseAuthenticatedService<T, Et, Id> {
//    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery, Map<String, Object> extra) throws Exception;
//    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, Class<T> entityClass, String queryStr, Map<String, Object> extra) throws Exception;
}
