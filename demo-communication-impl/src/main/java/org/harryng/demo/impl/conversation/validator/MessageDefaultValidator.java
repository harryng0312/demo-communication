package org.harryng.demo.impl.conversation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.harryng.demo.impl.conversation.dto.AbstractMessage;

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
