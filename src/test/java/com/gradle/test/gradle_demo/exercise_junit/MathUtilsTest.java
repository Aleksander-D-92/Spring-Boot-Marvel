package com.gradle.test.gradle_demo.exercise_junit;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {
    private MathUtils mathUtils;
    static int a;

    @BeforeAll
    static void beforeAllInit() {
        a = 20;
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void init() {
        System.out.println(a);
        System.out.println("@BeforeEach");
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanUp() {
        System.out.println("@AfterEach");
    }

    @RepeatedTest(3)
    @DisplayName("add()")
    void add(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getTotalRepetitions());
        System.out.println(repetitionInfo.getCurrentRepetition());
        int result = mathUtils.add(7, 3);
        assertNotEquals(5, result);
        assertEquals(10, result);
        assertAll(
                () -> assertEquals(10, result, () -> "supplier string"),
                () -> assertNotEquals(5, result),
                () -> assertNotEquals(12, result),
                () -> assertEquals(12, mathUtils.add(6, 6))
        );
    }

    @Test
    @DisplayName("divide()")
    void divide() {
        boolean isServerUp = false;
        assumeTrue(true); // if false return;
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(7, 0), "Divide by zero");
        assertDoesNotThrow(() -> mathUtils.divide(7, 1), "Divide by zero");
    }

    @Test
    void computeCircleArea() {
        assertEquals(706.8583470577034, mathUtils.computeCircleArea(15), "message");
    }

    @Test
    void computeCircleArea2() {
        MathUtils mock = mock(MathUtils.class);
        when(mock.multiply(2, 2)).thenReturn(4);
        assertEquals(4, mathUtils.multiply(2, 2), "from multiply");
        assertEquals(4, mock.multiply(2, 2), "from multiply");
    }
}
