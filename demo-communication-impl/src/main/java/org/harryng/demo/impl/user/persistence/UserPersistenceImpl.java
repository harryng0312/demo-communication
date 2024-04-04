package org.harryng.demo.impl.user.persistence;

import org.harryng.demo.impl.base.persistence.AbstractSearchablePersistence;
import org.harryng.demo.api.user.entity.UserImpl;
import org.harryng.demo.api.user.persistence.UserPersistence;

public class UserPersistenceImpl extends AbstractSearchablePersistence<UserImpl, Long> implements UserPersistence {
    public UserPersistenceImpl() {
        super(UserImpl.class);
    }
}
