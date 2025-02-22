package org.harryng.demo.impl.base.persistence;

import jakarta.persistence.EntityManager;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import java.io.Serializable;

public class SimpleBaseSearchablePersistence<Ent extends BaseModel<Id>, Id extends Serializable>
        extends SimpleBasePersistence<Ent, Id> implements BaseSearchablePersistence<Ent, Id> {

    public SimpleBaseSearchablePersistence(JpaEntityInformation<Ent, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public SimpleBaseSearchablePersistence(Class<Ent> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

//    @Override
//    public PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception {
//        return PersistenceUtil.selectObjectByCriteria(getEntityManager(), pageInfo, criteriaQuery);
//    }
//
//    @Override
//    public PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws Exception {
//        return PersistenceUtil.selectObjectByQuery(getEntityManager(), pageInfo, getDomainClass(), queryStr);
//    }
}
