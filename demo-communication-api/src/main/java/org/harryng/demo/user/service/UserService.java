package org.harryng.demo.user.service;

import org.harryng.demo.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.user.entity.UserImpl;

import java.util.Map;

public interface UserService extends BaseSearchableAuthenticatedService<UserImpl, Long> {
    UserImpl getByUsername(String username, Map<String, Object> extra) throws Exception;
}
