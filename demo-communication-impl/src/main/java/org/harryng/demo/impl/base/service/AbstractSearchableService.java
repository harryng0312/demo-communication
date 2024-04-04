package org.harryng.demo.impl.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;

import java.io.Serializable;
import java.util.Map;


public abstract class AbstractSearchableService<T extends BaseModel<Id>, Id extends Serializable>
        extends AbstractService<T, Id> implements BaseSearchableAuthenticatedService<T, Id> {

    @Override
    public abstract BaseSearchablePersistence<T, Id> getPersistence();

    @Override
    public PageResult<T> findByConditions(
            SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery,
            Map<String, Object> extra) throws Exception {
        return getPersistence().selectByConditions(pageInfo, criteriaQuery);
    }

    @Override
    public PageResult<T> findByConditions(
            SessionHolder sessionHolder, PageInfo pageInfo, Class<T> entityClass, String queryStr, Map<String, Object> extra) throws Exception {
        return getPersistence().selectByConditions(pageInfo, getPersistence().getEntityClass(), queryStr);
    }
}
