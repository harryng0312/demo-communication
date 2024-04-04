package org.harryng.demo.api.base.persistence;

import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;

import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseSearchablePersistence<T extends BaseModel<Id>, Id extends Serializable>
        extends BasePersistence<T, Id>, JpaRepository<T, Id> {
//    PageResult<T> selectByConditions(PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws Exception;
//    PageResult<T> selectByConditions(PageInfo pageInfo, Class<T> domainClass, String queryStr) throws Exception;
}
