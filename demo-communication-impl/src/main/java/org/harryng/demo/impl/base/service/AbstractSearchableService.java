package org.harryng.demo.impl.base.service;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.impl.base.persistence.BaseSearchablePersistence;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractSearchableService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable>
        extends AbstractService<Ent, Dget, Dadd, Dedit, Id> implements BaseSearchableValidatedService<Ent, Dget, Dadd, Dedit, Id> {

    @Override
    public abstract BaseSearchablePersistence<Ent, Id> getPersistence();

    @Override
    public PageResult<Dget> findByConditions(
            SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<Ent> criteriaQuery,
            Map<String, Object> extra) throws Exception {
        final PageResult<Ent> entPageResult = getPersistence().selectByConditions(pageInfo, criteriaQuery);
        return PageResult.fromPageResult(entPageResult, getMapper()::convertEntToGetDto);
    }

    @Override
    public PageResult<Dget> findByConditions(
            SessionHolder sessionHolder, PageInfo pageInfo, Class<Ent> entClass, String queryStr, Map<String, Object> extra) throws Exception {
        final PageResult<Ent> entPageResult = getPersistence().selectByConditions(pageInfo, getPersistence().getDomainClass(), queryStr);
        return PageResult.fromPageResult(entPageResult, getMapper()::convertEntToGetDto);
    }
}
