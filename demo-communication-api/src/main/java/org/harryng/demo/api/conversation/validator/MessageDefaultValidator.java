package org.harryng.demo.api.conversation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.harryng.demo.api.conversation.dto.AbstractMessage;

public class MessageDefaultValidator implements ConstraintValidator<MessageDefaultValidated, AbstractMessage> {

    @Override
    public void initialize(MessageDefaultValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AbstractMessage abstractMessage, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
