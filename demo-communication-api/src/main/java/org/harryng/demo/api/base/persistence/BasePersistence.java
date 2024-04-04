package org.harryng.demo.api.base.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.harryng.demo.api.base.entity.BaseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


//public interface BasePersistence<T extends BaseModel<Id>, Id extends Serializable> {
//    EntityManager getEntityManager();
//    EntityManager getEntityManager(String entityManagerName);
//    Class<T> getEntityClass();
//
//    T selectById(Id id) throws Exception;
//    int insert(T obj) throws Exception;
//    int update(T obj) throws Exception;
//    int delete(Id id) throws Exception;
//}
@NoRepositoryBean
public interface BasePersistence<T extends BaseModel<Id>, Id extends Serializable> extends CrudRepository<T, Id> {
//    @PersistenceContext(name = "entityManagerFactory")
    EntityManager getEntityManager();
//    EntityManager getEntityManager(String entityManagerName);
//    Class<T> getEntityClass();
    Class<T> getDomainClass();

//    default int delete(Id id) {
//        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        final CriteriaDelete<T> criteriaDelete = cb.createCriteriaDelete(getEntityClass());
//        final Root<T> root = criteriaDelete.from(getEntityClass());
//        criteriaDelete.where(cb.equal(root.get("id"), id));
//        final Query query = entityManager.createQuery(criteriaDelete);
//        return query.executeUpdate();
//    }

}
