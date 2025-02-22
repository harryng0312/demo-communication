package org.harryng.demo.impl.base.service;

import org.harryng.demo.impl.base.entity.BaseModel;

import java.io.Serializable;

public abstract class AbstractSearchableService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable>
        extends AbstractService<Ent, Dget, Dadd, Dedit, Id> implements BaseSearchableValidatedService<Ent, Dget, Dadd, Dedit, Id> {

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
