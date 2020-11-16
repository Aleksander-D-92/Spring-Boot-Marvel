package com.gradle.test.gradle_demo.controllers;

import com.gradle.test.gradle_demo.configuration.security.JWT;
import com.gradle.test.gradle_demo.configuration.security.JWTProvider;
import com.gradle.test.gradle_demo.domain.User;
import com.gradle.test.gradle_demo.dto.user.binding.UserChangePasswordForm;
import com.gradle.test.gradle_demo.dto.user.binding.UserLoginForm;
import com.gradle.test.gradle_demo.dto.user.binding.UserRegisterForm;
import com.gradle.test.gradle_demo.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserAccountController {
    private final JWTProvider jwtProvider;
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("users/login")
    public ResponseEntity<JWT> authenticate(@Valid @RequestBody UserLoginForm form) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        User user = (User) this.authenticationManager.authenticate(token).getPrincipal();
        JWT jwt = new JWT();
        jwt.setValue(jwtProvider.createJWT(user, true));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("users/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterForm form) {
        this.userService.register(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("users/change-password")
    public ResponseEntity<String> parseJWT(@Valid @RequestBody UserChangePasswordForm form) {
        System.out.println("test");
        return new ResponseEntity<>("das", HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<String> landingPage() {
        return new ResponseEntity<>("new JWT()", HttpStatus.OK);
    }
}
