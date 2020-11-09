package com.gradle.test.gradle_demo.exercise_junit;

import org.springframework.stereotype.Component;

@Component
public class MathUtils {
    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double computeCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }
}
