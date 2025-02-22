package org.harryng.demo.impl.asset.persistence;

import org.harryng.demo.impl.base.persistence.BaseSearchablePersistence;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetPersistence extends BaseSearchablePersistence<AssetImpl, Long> {
    Page<AssetImpl> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
