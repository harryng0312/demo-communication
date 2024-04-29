package org.harryng.demo.api.base.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

public class SimpleBasePersistence<T extends BaseModel<Id>, Id extends Serializable>
        extends SimpleJpaRepository<T, Id> implements BasePersistence<T, Id> {

    private final EntityManager entityManager;

    public SimpleBasePersistence(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public SimpleBasePersistence(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public @NonNull Class<T> getDomainClass() {
        return super.getDomainClass();
    }

    @Override
//    @Transactional
    public void deleteById(@NonNull Id id) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaDelete<T> criteriaDelete = cb.createCriteriaDelete(getDomainClass());
        final Root<T> root = criteriaDelete.from(getDomainClass());
        criteriaDelete.where(cb.equal(root.get("id"), id));
        final Query query = getEntityManager().createQuery(criteriaDelete);
        query.executeUpdate();
    }
}
