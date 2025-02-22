package org.harryng.demo.impl.user.service;

import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.service.BaseSearchableValidatedService;
import org.harryng.demo.impl.user.dto.UserDto;
import org.harryng.demo.impl.user.entity.UserImpl;

import java.util.Map;
import java.util.Optional;

public interface UserService extends BaseSearchableValidatedService<UserImpl, UserDto, UserDto, UserDto, Long> {
    Optional<UserDto> getByUsername(String username, Map<String, Object> extra) throws Exception;

    Optional<UserDto> getByMyId(SessionHolder sessionHolder, Map<String, Object> extra) throws Exception;

    Optional<UserDto> getById(
            SessionHolder sessionHolder, Long id, boolean loadUsergroups, boolean loadRoles,
            boolean loadPermissions, Map<String, Object> extra) throws Exception;
}
