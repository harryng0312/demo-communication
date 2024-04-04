package org.harryng.demo.api.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;

import java.io.Serializable;
import java.util.Map;

public interface BaseSearchableAuthenticatedService<T extends BaseModel<Id>, Id extends Serializable>
        extends BaseAuthenticatedService<T, Id> {
//    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery, Map<String, Object> extra) throws Exception;
//    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, Class<T> entityClass, String queryStr, Map<String, Object> extra) throws Exception;
}
