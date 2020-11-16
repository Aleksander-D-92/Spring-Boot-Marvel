package com.gradle.test.gradle_demo.controllers.for_testing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Getter
@Setter
public class HelloServiceImpl {
    public String hello() {
        return "pesho";
    }
}
