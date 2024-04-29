package org.harryng.demo.api.user.service;

import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.user.dto.UserDto;
import org.harryng.demo.api.user.entity.UserImpl;

import java.util.Map;
import java.util.Optional;

public interface UserService extends BaseSearchableAuthenticatedService<UserDto, UserImpl, Long> {
    Optional<UserDto> getByUsername(String username, Map<String, Object> extra) throws Exception;
    Optional<UserDto> getByMyId(SessionHolder sessionHolder, Map<String, Object> extra) throws Exception;
    Optional<UserDto> getById(
            SessionHolder sessionHolder, Long id, boolean loadUsergroups, boolean loadRoles,
            boolean loadPermissions, Map<String, Object> extra) throws Exception;
}
