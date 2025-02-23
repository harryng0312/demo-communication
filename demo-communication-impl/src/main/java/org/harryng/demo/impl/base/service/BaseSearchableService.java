//package org.harryng.demo.impl.base.service;
//
//import jakarta.persistence.criteria.CriteriaQuery;
//import org.harryng.demo.api.util.SessionHolder;
//import org.harryng.demo.impl.base.entity.BaseModel;
//import org.harryng.demo.api.util.PageInfo;
//import org.harryng.demo.api.util.PageResult;
//
//import java.io.Serializable;
//import java.util.Map;
//
//public interface BaseSearchableService<
//        Ent extends BaseModel<Id>,
//        Dget extends BaseModel<Id>,
//        Dadd extends BaseModel<Id>,
//        Dedit extends BaseModel<Id>,
//        Id extends Serializable> extends BaseService<Ent, Dget, Dadd, Dedit, Id> {
////    PageResult<Dget> findByConditions(PageInfo pageInfo, CriteriaQuery<Ent> criteriaQuery) throws Exception;
////    PageResult<Dget> findByConditions(PageInfo pageInfo, Class<Ent> entityClass, String queryStr) throws Exception;
//
//    PageResult<Dget> findByConditions(
//            SessionHolder sessionHolder, PageInfo pageInfo, CriteriaQuery<Ent> criteriaQuery,
//            Map<String, Object> extra) throws Exception;
//
//    PageResult<Dget> findByConditions(
//            SessionHolder sessionHolder, PageInfo pageInfo, Class<Ent> entClass, String queryStr, Map<String, Object> extra) throws Exception;
//}
