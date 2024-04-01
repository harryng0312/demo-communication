package org.harryng.demo.user.service;

import org.harryng.demo.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.base.service.BaseSearchableService;
import org.harryng.demo.user.pojo.data.entity.UserImpl;

import java.util.Map;

public interface UserService extends BaseSearchableAuthenticatedService<Long, UserImpl> {
    UserImpl getByUsername(String username, Map<String, Object> extra) throws Exception;
}
