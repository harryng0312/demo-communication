package org.harryng.demo.base.persistence;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.base.pojo.entity.BaseEntity;

import java.io.Serializable;

public abstract class AbstractPersistence<Id extends Serializable, T extends BaseEntity<Id>> implements BasePersistence<Id, T> {

//    @Resource(name = "entityManagerFactory")
    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager defaultEntityManager;
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
    public T selectById(Id id) throws RuntimeException, Exception {
        return getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public int insert(T obj) throws RuntimeException, Exception {
        getEntityManager().persist(obj);
        return 1;
    }

    @Override
    public int update(T obj) throws RuntimeException, Exception {
        getEntityManager().merge(obj);
        return 1;
    }

    @Override
    public int delete(Id id) throws RuntimeException, Exception {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaDelete<T> criteriaDelete = cb.createCriteriaDelete(getEntityClass());
        final Root<T> root = criteriaDelete.from(getEntityClass());
        criteriaDelete.where(cb.equal(root.get("id"), id));
        final Query query = getEntityManager().createQuery(criteriaDelete);
        return query.executeUpdate();
    }
}
