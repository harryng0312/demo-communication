package org.harryng.demo.impl.base.service;

import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;

import java.io.Serializable;

public abstract class AbstractSearchableService<T extends BaseModel<Id>, Et extends BaseModel<Id>, Id extends Serializable>
        extends AbstractService<T, Et, Id> implements BaseSearchableAuthenticatedService<T, Et, Id> {

//    @Override
//    public abstract BaseSearchablePersistence<T, Id> getPersistence();
//
//    @Override
//    public PageResult<T> findByConditions(
//            SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery,
//            Map<String, Object> extra) throws Exception {
//        return getPersistence().selectByConditions(pageInfo, criteriaQuery);
//    }
//
//    @Override
//    public PageResult<T> findByConditions(
//            SessionHolder sessionHolder, PageInfo pageInfo, Class<T> domainClass, String queryStr, Map<String, Object> extra) throws Exception {
//        return getPersistence().selectByConditions(pageInfo, getPersistence().getDomainClass(), queryStr);
//    }
}
