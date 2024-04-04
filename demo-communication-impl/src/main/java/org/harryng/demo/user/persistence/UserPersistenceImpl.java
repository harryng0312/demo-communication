package org.harryng.demo.user.persistence;

import org.harryng.demo.base.persistence.AbstractSearchablePersistence;
import org.harryng.demo.user.entity.UserImpl;

public class UserPersistenceImpl extends AbstractSearchablePersistence<UserImpl, Long> implements UserPersistence {
    public UserPersistenceImpl() {
        super(UserImpl.class);
    }
}
