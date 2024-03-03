package org.harryng.demo.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.base.pojo.entity.BaseEntity;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import java.io.Serializable;


public abstract class AbstractSearchableService<Id extends Serializable, T extends BaseEntity<Id>> extends AbstractService<Id, T> implements BaseSearchableService<Id, T> {

    @Override
    public abstract BaseSearchablePersistence<Id, T> getPersistence();

    @Override
    public PageResult<T> findByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception {
        return getPersistence().selectByConditions(pageInfo, criteriaQuery);
    }

    @Override
    public PageResult<T> findByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws Exception {
        return getPersistence().selectByConditions(pageInfo, getPersistence().getEntityClass(), queryStr);
    }
}
