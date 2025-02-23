//package org.harryng.demo.impl.base.service;
//
//import lombok.NonNull;
//import org.harryng.demo.api.util.SessionHolder;
//import org.harryng.demo.api.util.ValidationResult;
//import org.harryng.demo.impl.base.entity.BaseModel;
//import org.harryng.demo.impl.base.persistence.BasePersistence;
//
//import java.io.Serializable;
//import java.util.Map;
//import java.util.Optional;
//
//public interface BaseService<
//        Ent extends BaseModel<Id>,
//        Dget extends BaseModel<Id>,
//        Dadd extends BaseModel<Id>,
//        Dedit extends BaseModel<Id>,
//        Id extends Serializable> {
//    BasePersistence<Ent, Id> getPersistence();
//
//    Optional<Dget> getById(@NonNull SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
//
//    int add(@NonNull SessionHolder sessionHolder, Dadd obj, Map<String, Object> extra) throws Exception;
//
//    int edit(@NonNull SessionHolder sessionHolder, Dedit obj, Map<String, Object> extra) throws Exception;
//
//    int remove(@NonNull SessionHolder sessionHolder, Id id, Map<String, Object> extra) throws Exception;
//}
