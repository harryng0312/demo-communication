package org.harryng.demo.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.base.entity.BaseModel;
import org.harryng.demo.base.dto.SessionHolder;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;

import java.io.Serializable;
import java.util.Map;


public abstract class AbstractSearchableService<Id extends Serializable, T extends BaseModel<Id>> extends AbstractService<Id, T>
        implements BaseSearchableAuthenticatedService<Id, T> {

    @Override
    public abstract BaseSearchablePersistence<Id, T> getPersistence();

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
