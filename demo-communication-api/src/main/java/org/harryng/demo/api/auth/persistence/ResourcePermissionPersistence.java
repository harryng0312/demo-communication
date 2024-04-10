package org.harryng.demo.api.auth.persistence;

import org.harryng.demo.api.auth.entity.ResourcePermissionImpl;
import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcePermissionPersistence extends BaseSearchablePersistence<ResourcePermissionImpl, Long> {
    @Query("select u from ResourcePermissionImpl u where u.roleId in :roleIds")
    List<ResourcePermissionImpl> getByRoleIds(@Param("roleIds") List<Long> roleIds);
}
