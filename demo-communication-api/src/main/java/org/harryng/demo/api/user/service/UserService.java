package org.harryng.demo.api.user.service;

import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.user.entity.UserImpl;

import java.util.Map;
import java.util.Optional;

public interface UserService extends BaseSearchableAuthenticatedService<UserImpl, Long> {
    Optional<UserImpl> getByUsername(String username, Map<String, Object> extra) throws Exception;
    Optional<UserImpl> getById(
            SessionHolder sessionHolder, Long id, boolean loadUsergroupIds, boolean loadRoleIds,
            boolean loadPermissionIds, Map<String, Object> extra) throws Exception;
}
