package com.gradle.test.gradle_demo.dto.user.confirm_password_validation;


import com.gradle.test.gradle_demo.dto.user.binding.UserRegisterForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPassword implements ConstraintValidator<ValidateConfirmPassword, UserRegisterForm> {


    @Override
    public boolean isValid(UserRegisterForm form, ConstraintValidatorContext context)
    {
        return form.getPassword().equals(form.getConfirmPassword());
    }
}
