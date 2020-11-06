package com.gradle.test.gradle_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginForm {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
