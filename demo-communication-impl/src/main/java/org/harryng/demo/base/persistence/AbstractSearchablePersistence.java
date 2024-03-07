package org.harryng.demo.base.persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.base.pojo.data.model.BaseModel;
import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;
import org.harryng.demo.util.persistence.PersistenceUtil;

import java.io.Serializable;


public abstract class AbstractSearchablePersistence<Id extends Serializable, T extends BaseModel<Id>> extends AbstractPersistence<Id, T> implements BaseSearchablePersistence<Id, T> {

    public AbstractSearchablePersistence(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws RuntimeException, Exception {
        PageResult<T> pageResult = PersistenceUtil.selectObjectByCriteria(getEntityManager(), pageInfo, criteriaQuery);
        return pageResult;
    }

    @Override
    public PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> entityClass, String queryStr) throws RuntimeException, Exception {
        PageResult<T> pageResult = PersistenceUtil.selectObjectByQuery(getEntityManager(), pageInfo, getEntityClass(), queryStr);
        return pageResult;
    }
}
