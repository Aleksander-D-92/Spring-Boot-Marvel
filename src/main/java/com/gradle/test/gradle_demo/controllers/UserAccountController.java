package com.gradle.test.gradle_demo.controllers;

import com.gradle.test.gradle_demo.configuration.security.JWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {
    @PostMapping("/login")
    public ResponseEntity<JWT> authenticate() {

        return new ResponseEntity<>(new JWT(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> landingPage() {

        return new ResponseEntity<>("new JWT()", HttpStatus.OK);
    }
}
