package com.practice.sharemate.validators.validation;

import com.practice.sharemate.exceptions.DublicateEmailException;
import com.practice.sharemate.user.repository.UserRepository;
import com.practice.sharemate.validators.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (email == null) {
            return true;
        }

        if ( userRepository.findByEmail(email) == null ) {
            return true;
        } else { throw new DublicateEmailException("Пользователь с такой электронной почтой уже существует"); }
    }
}
