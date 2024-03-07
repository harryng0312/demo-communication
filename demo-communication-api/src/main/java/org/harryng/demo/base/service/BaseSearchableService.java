package org.harryng.demo.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.base.pojo.data.model.BaseModel;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import java.io.Serializable;

public interface BaseSearchableService<Id extends Serializable, T extends BaseModel<Id>> extends BaseService<Id, T> {
    PageResult<T> findByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws RuntimeException, Exception;
    PageResult<T> findByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws RuntimeException, Exception;
}
