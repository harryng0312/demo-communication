package org.harryng.demo.impl.auth.persistence;

import org.harryng.demo.impl.auth.entity.ResourcePermissionImpl;
import org.harryng.demo.impl.base.persistence.BaseSearchablePersistence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcePermissionPersistence extends BaseSearchablePersistence<ResourcePermissionImpl, Long> {
    @Query("select u from ResourcePermissionImpl u where u.roleId in :roleIds order by u.id")
    List<ResourcePermissionImpl> getByRoleIds(@Param("roleIds") List<Long> roleIds);
}
