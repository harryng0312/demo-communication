package org.harryng.demo.base.persistence;

import org.harryng.demo.base.pojo.data.model.BaseModel;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import jakarta.persistence.criteria.CriteriaQuery;

import java.io.Serializable;

public interface BaseSearchablePersistence<Id extends Serializable, T extends BaseModel<Id>> extends BasePersistence<Id, T> {
    PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws RuntimeException, Exception;
    PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws RuntimeException, Exception;
}
