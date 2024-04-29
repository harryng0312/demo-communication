package org.harryng.demo.api.user.persistence;

import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.api.user.entity.UserGroupImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupPersistence extends BaseSearchablePersistence<UserGroupImpl, Long>{

    @Query("select ug from UserUserGroupImpl uug " +
            "inner join UserGroupImpl ug on ug.id = uug.usergroupId " +
            "where uug.userId = :userId order by uug.usergroupId")
    List<UserGroupImpl> findAllByUserId(@Param("userId") Long userId);
}
