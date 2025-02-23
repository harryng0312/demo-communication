package org.harryng.demo.impl.base.persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseSearchablePersistence<Ent extends BaseModel<Id>, Id extends Serializable>
        extends BasePersistence<Ent, Id>, JpaRepository<Ent, Id> {
    PageResult<Ent> selectByConditions(PageInfo pageInfo, CriteriaQuery<Ent> criteriaQuery) throws Exception;
    PageResult<Ent> selectByConditions(PageInfo pageInfo, Class<Ent> domainClass, String queryStr) throws Exception;
}
