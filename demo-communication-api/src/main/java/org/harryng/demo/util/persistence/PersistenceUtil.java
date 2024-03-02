package org.harryng.demo.util.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import org.harryng.demo.util.PageInfo;
import org.harryng.demo.util.PageResult;
import org.hibernate.StatelessSession;

public class PersistenceUtil {

    public static <T extends Object> PageResult<T> selectObjectByCriteria(StatelessSession statelessSession, PageInfo pageInfo, CriteriaQuery<T> criteriaQuery) throws RuntimeException, Exception {
        TypedQuery<T> typedQuery = statelessSession.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageInfo.getStartRowIndex());
        typedQuery.setMaxResults(pageInfo.getPageSize());
        PageResult<T> pageResult = new PageResult<>(pageInfo);
        pageResult.getResults().addAll(typedQuery.getResultList());
        return pageResult;
    }

    public static <T extends Object> PageResult<T> selectObjectByQuery(StatelessSession statelessSession, PageInfo pageInfo, Class<T> typeClass, String queryStr) throws RuntimeException, Exception {
        PageResult<T> pageResult = new PageResult<>(pageInfo);
        TypedQuery<T> typedQuery = statelessSession.createQuery(queryStr, typeClass);
        typedQuery.setFirstResult((int) pageInfo.getStartRowIndex());
        typedQuery.setMaxResults(pageInfo.getPageSize());
        pageResult.getResults().addAll(typedQuery.getResultList());
        return pageResult;
    }
}
