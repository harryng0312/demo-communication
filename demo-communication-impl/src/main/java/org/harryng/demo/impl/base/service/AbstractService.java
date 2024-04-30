package org.harryng.demo.impl.base.service;

import lombok.NonNull;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.entity.BaseModel;
import org.harryng.demo.api.base.persistence.BasePersistence;
import org.harryng.demo.api.base.service.BaseAuthenticatedService;
import org.harryng.demo.impl.base.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractService<Dto extends BaseModel<Id>, Et extends BaseModel<Id>, Id extends Serializable>
        implements BaseAuthenticatedService<Dto, Et, Id> {

    @Override
    public abstract BasePersistence<Et, Id> getPersistence();

    public abstract BaseMapper<Dto, Et> getMapper();

    @Override
    public @NonNull Optional<Dto> getById(@NonNull SessionHolder sessionHolder, @NonNull Id id, Map<String, Object> extra) {
        final var result = getPersistence().findById(id);
        return result.map(et -> getMapper().toDto(et));
    }

    @Override
    public Dto add(@NonNull SessionHolder sessionHolder, @NonNull Dto obj, Map<String, Object> extra) throws Exception {
        final Et inputEntity = getMapper().toEntity(obj);
        if(inputEntity == null) {
            throw new NullPointerException("inputEntity is null");
        }
        final Et entity = getPersistence().save(inputEntity);
        return getMapper().toDto(entity);
    }

    @Override
    public Dto edit(@NonNull SessionHolder sessionHolder, @NonNull Dto obj, Map<String, Object> extra) throws Exception {
        final Et inputEntity = getMapper().toEntity(obj);
        if(inputEntity == null) {
            throw new NullPointerException("inputEntity is null");
        }
        final Et entity = getPersistence().save(inputEntity);
        return getMapper().toDto(entity);
    }

    @Override
    public void remove(@NonNull SessionHolder sessionHolder, @NonNull Id id, @NonNull Map<String, Object> extra) throws Exception {
        getPersistence().deleteById(id);
    }
}
