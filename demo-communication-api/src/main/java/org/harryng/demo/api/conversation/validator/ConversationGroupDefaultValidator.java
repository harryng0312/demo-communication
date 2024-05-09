package org.harryng.demo.api.conversation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.harryng.demo.api.conversation.dto.AbstractMessage;

public class ConversationGroupDefaultValidator implements ConstraintValidator<ConversationGroupDefaultValidated, AbstractMessage> {

    @Override
    public void initialize(ConversationGroupDefaultValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AbstractMessage abstractMessage, ConstraintValidatorContext constraintValidatorContext) {
        // validate displayName
        return true;
    }
}
