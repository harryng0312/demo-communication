package org.harryng.demo.impl.base.service;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.NonNull;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.entity.BaseModel;
import org.harryng.demo.impl.base.entity.BaseModifiedModel;
import org.harryng.demo.impl.base.persistence.BasePersistence;
import org.harryng.demo.api.util.ValidationError;
import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.impl.base.mapper.BaseMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public abstract class AbstractService<
        Ent extends BaseModel<Id>,
        Dget extends BaseModel<Id>,
        Dadd extends BaseModel<Id>,
        Dedit extends BaseModel<Id>,
        Id extends Serializable>
        implements BaseValidatedService<Ent, Dget, Dadd, Dedit, Id> {

    @Resource
    protected Validator validator;

    @Override
    public abstract BasePersistence<Ent, Id> getPersistence();

    public abstract BaseMapper<Ent, Dget, Dadd, Dedit> getMapper();

    @Override
    public @NonNull Optional<Dget> getById(@NonNull SessionHolder sessionHolder, @NonNull Id id, Map<String, Object> extra) throws Exception {
        final var result = getPersistence().findById(id);
        return result.map(et -> getMapper().convertEntToGetDto(et));
    }

    @Override
    public ValidationResult<Dget> add(@NonNull SessionHolder sessionHolder, @NonNull Dadd obj, Map<String, Object> extra) throws Exception {
        final ValidationResult<Dget> result;
        final Set<ConstraintViolation<Dadd>> validationResult = validator.validate(obj);
//        final Function<Dto, Set<ConstraintViolation<Dto>>> validateFunc = (Function<Dto, Set<ConstraintViolation<Dto>>>) extra.get(ExtraParam.VALIDATE_FUNC);
//        if(validateFunc != null) {
//            final Set<ConstraintViolation<Dto>> subValResult = validateFunc.apply(obj);
//            validationResult.addAll(subValResult);
//        }
        result = ValidationResult.fromConstraintViolation(validationResult);
        if (validationResult.isEmpty()) {
            final Ent inputEntity = getMapper().convertAddDtoToEnt(obj);
            if (Objects.isNull(inputEntity)) {
                return ValidationResult.fromConstraintViolation(validationResult);
            }
            if (inputEntity instanceof BaseModifiedModel modifiedModel) {
                final var now = LocalDateTime.now();
                modifiedModel.setCreatedDate(now);
                modifiedModel.setModifiedDate(now);
            }
            final Ent entity = getPersistence().save(inputEntity);
            final Dget outputDto = getMapper().convertEntToGetDto(entity);
            result.setValue(outputDto);
//        } else {
//            final List<ValidationError> validationErrors = validationResult.stream()
//                    .map(dtoConstraintViolation -> ValidationError.of(
//                            dtoConstraintViolation.getPropertyPath().toString(),
//                            dtoConstraintViolation.getMessage(),
//                            dtoConstraintViolation.getInvalidValue()))
//                    .toList();
//            result.setValue(null);
//            result.getValidationErrors().addAll(validationErrors);
//            result = ValidationResult.fromConstraintViolation(validationResult);
        }
        return result;
    }

    @Override
    public ValidationResult<Dget> edit(@NonNull SessionHolder sessionHolder, @NonNull Dedit obj, Map<String, Object> extra) throws Exception {
        final var result = new ValidationResult<Dget>();
        final Set<ConstraintViolation<Dedit>> validationResult = validator.validate(obj);
        if (validationResult.isEmpty()) {
            final Ent inputEntity = getMapper().convertEditDtoToEnt(obj);
            if (Objects.isNull(inputEntity)) {
                return ValidationResult.fromConstraintViolation(validationResult);
            }
            if (inputEntity instanceof BaseModifiedModel modifiedModel) {
                final var now = LocalDateTime.now();
                modifiedModel.setModifiedDate(now);
            }
            final Ent entity = getPersistence().save(inputEntity);
            final Dget outputDto = getMapper().convertEntToGetDto(entity);
            result.setValue(outputDto);
        } else {
            final List<ValidationError> validationErrors = validationResult.stream()
                    .map(dtoConstraintViolation -> ValidationError.of(
                            dtoConstraintViolation.getPropertyPath().toString(),
                            dtoConstraintViolation.getMessage(),
                            dtoConstraintViolation.getInvalidValue()))
                    .toList();
            result.setValue(null);
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
