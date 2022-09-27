package com.softserveinc.dokazovi.validator;

import com.softserveinc.dokazovi.annotations.PasswordMatches;
import com.softserveinc.dokazovi.dto.user.UserPasswordDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class PasswordMatchesValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    PasswordMatches passwordMatches;

    @Test
    void isValidTest() {
        UserPasswordDTO passwordDTO = UserPasswordDTO.builder()
                .token("ef590bd8-e993-4153-8206-b963732bfeb9")
                .newPassword("1234qwer")
                .matchPassword("1234qwer")
                .build();
        PasswordMatchesValidator validator = new PasswordMatchesValidator();
        validator.initialize(passwordMatches);
        Assertions.assertTrue(validator.isValid(passwordDTO, constraintValidatorContext));
    }

    @Test
    void isValidTestPasswordsDoNotMatches() {
        UserPasswordDTO passwordDTO = UserPasswordDTO.builder()
                .token("ef590bd8-e993-4153-8206-b963732bfeb9")
                .newPassword("1234qwet")
                .matchPassword("1234qwer")
                .build();
        PasswordMatchesValidator validator = new PasswordMatchesValidator();
        validator.initialize(passwordMatches);
        Assertions.assertFalse(validator.isValid(passwordDTO, constraintValidatorContext));
    }

    @Test
    void isValidTestTokenIsEmpty() {
        UserPasswordDTO passwordDTO = UserPasswordDTO.builder()
                .newPassword("1234qwer")
                .matchPassword("1234qwer")
                .build();
        PasswordMatchesValidator validator = new PasswordMatchesValidator();
        validator.initialize(passwordMatches);
        Assertions.assertFalse(validator.isValid(passwordDTO, constraintValidatorContext));
    }

    @Test
    void isValidTestPasswordDoNotMatchPattern() {
        UserPasswordDTO passwordDTO = UserPasswordDTO.builder()
                .token("ef590bd8-e993-4153-8206-b963732bfeb9")
                .newPassword("1234q")
                .matchPassword("1234q")
                .build();
        PasswordMatchesValidator validator = new PasswordMatchesValidator();
        validator.initialize(passwordMatches);
        Assertions.assertFalse(validator.isValid(passwordDTO, constraintValidatorContext));
    }
}
