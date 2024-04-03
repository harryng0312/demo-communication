package org.harryng.demo.base.persistence;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.base.entity.BaseModel;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;

public abstract class AbstractPersistence<Id extends Serializable, T extends BaseModel<Id>> implements BasePersistence<Id, T> {

//    @Resource(name = "entityManagerFactory")
    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager defaultEntityManager;
    @Resource
    protected ApplicationContext applicationContext;
//    @Resource
//    private StatelessSession statelessSession = null;
    private Class<T> entityClass = null;

    public AbstractPersistence(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public EntityManager getEntityManager() {
        return defaultEntityManager;
    }

    @Override
    public EntityManager getEntityManager(String entityManagerName) {
        return null;
    }

    @Override
    public T selectById(Id id) throws Exception {
        return getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public int insert(T obj) throws Exception {
        getEntityManager().persist(obj);
        return 1;
    }

    @Override
    public int update(T obj) throws Exception {
        getEntityManager().merge(obj);
        return 1;
    }

    @Override
    public int delete(Id id) throws Exception {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaDelete<T> criteriaDelete = cb.createCriteriaDelete(getEntityClass());
        final Root<T> root = criteriaDelete.from(getEntityClass());
        criteriaDelete.where(cb.equal(root.get("id"), id));
        final Query query = getEntityManager().createQuery(criteriaDelete);
        return query.executeUpdate();
    }
}
