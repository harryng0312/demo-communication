package org.harryng.demo.impl.user.persistence;

import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.api.user.entity.UserImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserPersistence extends BaseSearchablePersistence<UserImpl, Long> {
    @Query("select u.usergroupId from UserUserGroupImpl u where u.userId = :userId")
    List<Long> getUsergroupIds(@Param("userId") Long userId);
    Page<UserImpl> searchByUsername(String username, Pageable pageable);
}
