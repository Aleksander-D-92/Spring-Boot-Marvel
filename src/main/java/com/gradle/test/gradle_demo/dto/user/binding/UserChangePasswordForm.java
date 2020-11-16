package com.gradle.test.gradle_demo.dto.user.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserChangePasswordForm {
    private String oldPassword;
    private String newPassword;
}
