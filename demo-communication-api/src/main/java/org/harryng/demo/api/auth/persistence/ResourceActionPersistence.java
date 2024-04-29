package org.harryng.demo.api.auth.persistence;

import org.harryng.demo.api.auth.entity.ResourceActionImpl;
import org.harryng.demo.api.base.persistence.BaseSearchablePersistence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceActionPersistence extends BaseSearchablePersistence<ResourceActionImpl, Long> {

    @Query(nativeQuery = true,
            value = "select ra.id_, ra.resource_type, ra.action_method, ra.action_bit " +
                    "from resourceaction ra " +
                    "where ra.action_bit & :actionFlag = ra.action_bit")
    List<ResourceActionImpl> findByActionFlag(@Param("actionFlag") Long actionFlag);
}
