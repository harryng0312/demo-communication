package org.harryng.demo.base.persistence;

import org.harryng.demo.base.pojo.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class AbstractPersistence<Id extends Object, T extends BaseEntity<Id>> implements BasePersistence<Id, T> {

    @Autowired
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
        return null;
    }

    @Override
    public int insert(T obj) throws RuntimeException, Exception {
        return 0;
    }

    @Override
    public int update(T obj) throws RuntimeException, Exception {
        return 0;
    }

    @Override
    public int delete(Id id) throws RuntimeException, Exception {
        return 0;
    }
}
