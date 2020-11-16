package com.gradle.test.gradle_demo.dto.user.binding;

import com.gradle.test.gradle_demo.dto.user.confirm_password_validation.ValidateConfirmPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ValidateConfirmPassword
public class UserRegisterForm {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
}
