package org.harryng.demo.base.persistence;

import org.harryng.demo.base.entity.BaseModel;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import jakarta.persistence.criteria.CriteriaQuery;

import java.io.Serializable;

public interface BaseSearchablePersistence<T extends BaseModel<Id>, Id extends Serializable> extends BasePersistence<T, Id> {
    PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception;
    PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws Exception;
}
