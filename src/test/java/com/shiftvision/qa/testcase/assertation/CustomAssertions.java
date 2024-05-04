package com.shiftvision.qa.testcase.assertation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomAssertions {
    public static void assertInteger(int expected, int actual) {
        assertEquals(expected, actual);
    }

    public static void assertDouble(double expected, double actual) {
        assertEquals(expected, actual);
    }

    public static void assertString(String expected, String actual) {
        assertEquals(expected, actual);
    }

}
