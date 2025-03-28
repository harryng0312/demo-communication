package org.harryng.demo.impl.auth.persistence;

import org.harryng.demo.impl.auth.entity.RoleImpl;
import org.harryng.demo.impl.base.persistence.BaseSearchablePersistence;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolePersistence extends BaseSearchablePersistence<RoleImpl, Long> {
    @Query("select r from RoleImpl r inner join UserGroupRoleImpl ugr on r.id = ugr.roleId " +
            "where ugr.usergroupId = :id order by r.id")
    List<RoleImpl> getIdsByUsergroupId(Long id);
}
