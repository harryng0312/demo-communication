package org.harryng.demo.api.user.service;

import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.user.entity.UserImpl;

import java.util.Map;

public interface UserService extends BaseSearchableAuthenticatedService<UserImpl, Long> {
    UserImpl getByUsername(String username, Map<String, Object> extra) throws Exception;
}
