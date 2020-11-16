package com.gradle.test.gradle_demo.dto.user.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginForm {
    @NotNull
    @Length(min = 5, max = 10)
    private String username;
    @NotNull
    private String password;
}
