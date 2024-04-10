package org.harryng.demo.api.user.persistence;

import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.api.user.entity.UserImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserPersistence extends BaseSearchablePersistence<UserImpl, Long> {
    Page<UserImpl> searchByUsername(String username, Pageable pageable);
}
