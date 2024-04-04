package org.harryng.demo.impl.base.persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;
import org.harryng.demo.api.util.persistence.PersistenceUtil;

import java.io.Serializable;


public abstract class AbstractSearchablePersistence<T extends BaseModel<Id>, Id extends Serializable>
        extends AbstractPersistence<T, Id> implements BaseSearchablePersistence<T, Id> {

    public AbstractSearchablePersistence(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws RuntimeException, Exception {
        return PersistenceUtil.selectObjectByCriteria(getEntityManager(), pageInfo, criteriaQuery);
    }

    @Override
    public PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws RuntimeException, Exception {
        return PersistenceUtil.selectObjectByQuery(getEntityManager(), pageInfo, getEntityClass(), queryStr);
    }
}
