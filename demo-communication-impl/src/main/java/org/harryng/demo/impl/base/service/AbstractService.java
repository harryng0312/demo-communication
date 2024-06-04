package org.harryng.demo.impl.base.service;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.NonNull;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.impl.base.persistence.BasePersistence;
import org.harryng.demo.api.util.ValidationError;
import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.impl.base.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractService<Dto extends BaseModel<Id>, Et extends BaseModel<Id>, Id extends Serializable>
        implements BaseAuthenticatedService<Dto, Et, Id> {

    @Resource
    protected Validator validator;

    @Override
    public abstract BasePersistence<Et, Id> getPersistence();

    public abstract BaseMapper<Dto, Et> getMapper();

    @Override
    public @NonNull Optional<Dto> getById(@NonNull SessionHolder sessionHolder, @NonNull Id id, Map<String, Object> extra) throws Exception {
        final var result = getPersistence().findById(id);
        return result.map(et -> getMapper().toDto(et));
    }

    @Override
    public ValidationResult<Dto> add(@NonNull SessionHolder sessionHolder, @NonNull Dto obj, Map<String, Object> extra) throws Exception {
        final var result = new ValidationResult<Dto>();
        final Set<ConstraintViolation<Dto>> validationResult = validator.validate(obj);
//        final Function<Dto, Set<ConstraintViolation<Dto>>> validateFunc = (Function<Dto, Set<ConstraintViolation<Dto>>>) extra.get(ExtraParam.VALIDATE_FUNC);
//        if(validateFunc != null) {
//            final Set<ConstraintViolation<Dto>> subValResult = validateFunc.apply(obj);
//            validationResult.addAll(subValResult);
//        }
        if (validationResult.isEmpty()) {
            final Et inputEntity = getMapper().toEntity(obj);
            if (inputEntity == null) {
                throw new NullPointerException("inputEntity is null");
            }
            final Et entity = getPersistence().save(inputEntity);
            final Dto outputDto = getMapper().toDto(entity);
            result.setValue(outputDto);
        } else {
            final List<ValidationError> validationErrors = validationResult.stream()
                    .map(dtoConstraintViolation -> ValidationError.of(
                            dtoConstraintViolation.getPropertyPath().toString(),
                            dtoConstraintViolation.getMessage(),
                            dtoConstraintViolation.getInvalidValue()))
                    .toList();
            result.setValue(obj);
            result.getValidationErrors().addAll(validationErrors);
        }
        return result;
    }

    @Override
    public ValidationResult<Dto> edit(@NonNull SessionHolder sessionHolder, @NonNull Dto obj, Map<String, Object> extra) throws Exception {
        final var result = new ValidationResult<Dto>();
        final Set<ConstraintViolation<Dto>> validationResult = validator.validate(obj);
        if (validationResult.isEmpty()) {
            final Et inputEntity = getMapper().toEntity(obj);
            if (inputEntity == null) {
                throw new NullPointerException("inputEntity is null");
            }
            final Et entity = getPersistence().save(inputEntity);
            final Dto outputDto = getMapper().toDto(entity);
            result.setValue(outputDto);
        } else {
            final List<ValidationError> validationErrors = validationResult.stream()
                    .map(dtoConstraintViolation -> ValidationError.of(
                            dtoConstraintViolation.getPropertyPath().toString(),
                            dtoConstraintViolation.getMessage(),
                            dtoConstraintViolation.getInvalidValue()))
                    .toList();
            result.setValue(obj);
            result.getValidationErrors().addAll(validationErrors);
        }
        return result;
    }

    @Override
    public ValidationResult<Id> remove(@NonNull SessionHolder sessionHolder, @NonNull Id id, @NonNull Map<String, Object> extra) throws Exception {
        getPersistence().deleteById(id);
        return new ValidationResult<>(id);
    }
}
