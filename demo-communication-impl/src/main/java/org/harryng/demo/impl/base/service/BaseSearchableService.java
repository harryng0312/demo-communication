package org.harryng.demo.impl.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;

import java.io.Serializable;

public interface BaseSearchableService<T extends BaseModel<Id>, Id extends Serializable> extends BaseService<T, Id> {
    PageResult<T> findByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception;
    PageResult<T> findByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws Exception;
}
