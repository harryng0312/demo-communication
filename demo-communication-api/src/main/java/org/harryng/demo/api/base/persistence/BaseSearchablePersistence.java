package org.harryng.demo.api.base.persistence;

import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;

import jakarta.persistence.criteria.CriteriaQuery;

import java.io.Serializable;

public interface BaseSearchablePersistence<T extends BaseModel<Id>, Id extends Serializable> extends BasePersistence<T, Id> {
    PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception;
    PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws Exception;
}
