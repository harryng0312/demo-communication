package org.harryng.demo.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.base.pojo.data.model.BaseModel;
import org.harryng.demo.base.pojo.dto.SessionHolder;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import java.io.Serializable;
import java.util.Map;

public interface BaseSearchableAuthenticatedService<Id extends Serializable, T extends BaseModel<Id>> extends BaseAuthenticatedService<Id, T> {
    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery, Map<String, Object> extra) throws Exception;
    PageResult<T> findByConditions(SessionHolder sessionHolder, PageInfo pageInfo, Class<T> entityClass, String queryStr, Map<String, Object> extra) throws Exception;
}
