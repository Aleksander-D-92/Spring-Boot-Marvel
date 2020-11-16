package com.gradle.test.gradle_demo.controllers.for_testing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloResource {
    private final HelloServiceImpl helloService;

    @GetMapping("/hello")
    public String helloWorld() {
        return helloService.hello();
    }

    @GetMapping("/json")
    public ResponseEntity<Hello> json() {
        return new ResponseEntity<>(new Hello("title-name", "value-name"), HttpStatus.OK);
    }

    @PostMapping(path = "/json")
    public ResponseEntity<Hello> postJson(@RequestBody Hello hello) {
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }

}
