package com.gradle.test.gradle_demo.controllers;

import com.gradle.test.gradle_demo.configuration.security.JWT;
import com.gradle.test.gradle_demo.dto.UserLoginForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserAccountController {
    @PostMapping("/login")
    public JWT authenticate(@Valid @RequestBody UserLoginForm form) {
        JWT jwt = new JWT();
        jwt.setJWT("dasasdsadsda");
        return jwt;
    }

    @GetMapping("/")
    public ResponseEntity<String> landingPage() {
        return new ResponseEntity<>("new JWT()", HttpStatus.OK);
    }
}
