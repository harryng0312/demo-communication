package org.harryng.demo.user.service;

import org.harryng.demo.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.base.service.AbstractSearchableService;
import org.harryng.demo.user.persistence.UserPersistence;
import org.harryng.demo.user.pojo.entity.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl extends AbstractSearchableService<Long, UserImpl> implements UserService {

    @Autowired
    private UserPersistence userPersistence;

    @Override
    public BaseSearchablePersistence<Long, UserImpl> getPersistence() {
        return userPersistence;
    }
}
