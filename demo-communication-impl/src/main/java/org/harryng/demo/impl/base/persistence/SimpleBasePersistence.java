package org.harryng.demo.impl.base.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

public class SimpleBasePersistence<Ent extends BaseModel<Id>, Id extends Serializable>
        extends SimpleJpaRepository<Ent, Id> implements BasePersistence<Ent, Id> {

    private final EntityManager entityManager;

    public SimpleBasePersistence(JpaEntityInformation<Ent, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public SimpleBasePersistence(Class<Ent> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public @NonNull Class<Ent> getDomainClass() {
        return super.getDomainClass();
    }

    @Override
//    @Transactional
    public void deleteById(@NonNull Id id) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaDelete<Ent> criteriaDelete = cb.createCriteriaDelete(getDomainClass());
        final Root<Ent> root = criteriaDelete.from(getDomainClass());
        criteriaDelete.where(cb.equal(root.get("id"), id));
        final Query query = getEntityManager().createQuery(criteriaDelete);
        query.executeUpdate();
    }
}
