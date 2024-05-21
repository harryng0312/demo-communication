package org.harryng.demo.impl.base.persistence;

import org.harryng.demo.impl.base.entity.BaseModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseSearchablePersistence<T extends BaseModel<Id>, Id extends Serializable>
        extends BasePersistence<T, Id>, JpaRepository<T, Id> {
//    PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception;
//    PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> domainClass, String queryStr) throws Exception;
}
