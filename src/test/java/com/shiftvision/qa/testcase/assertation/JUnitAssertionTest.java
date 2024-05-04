package com.shiftvision.qa.testcase.assertation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Function;
import static com.shiftvision.qa.testcase.assertation.CustomAssertions.*;


public class JUnitAssertionTest {
    @Test()
    @DisplayName("Expected age does not match with actual age")
    public void testInteger() {

        int actual = 10;
        int expected = 9;

        //Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual,
                "Expected age does not match with actual age");

    }

    @Test
    public void testInteger1() {

        int actual = 10;
        int expected = 10;

        //Assertions.assertEquals(expected, actual);
        assertEquals(expected, actual,
                "Expected age does not match with actual age");

        //WE x = fi();
        //boolean exist = x.isExists();
        //assertEquals(true, exist);

        boolean exist = true;
        assertTrue(exist);

    }
    @Test
    public void testInteger2() {

        int actual = 10;
        int expected = 10;

        assertInteger(expected, actual);
    }




    @Test
    public void testDouble() {

        double actual = 10.5;
        double expected = 10.6;

        //Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual,
                "Expected age does not match with actual age");

    }

    @Test
    public void testDouble2() {

        double actual = 10.5;
        double expected = 10.2;

        //Assertions.assertEquals(expected, actual);
        //Assertions.assertEquals(expected, actual, 0.1,
        //		"Expected age does not match with actual age");

        //Assertions.assertEquals(10.5, actual, 0.1,
        //		"Expected age does not match with actual age");

        //Assertions.assertEquals(10.6, 10.5, 0.1,
        //		"Expected age does not match with actual age");

        Assertions.assertEquals(10.55d, 10.58d, 0.03,
                "Expected age does not match with actual age");

    }




    @Test
    public void testChar() {

        char actual = 'A';
        char expected = 'A';

        //Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, actual,
                "Expected age does not match with actual age");

    }

    @Test
    public void testIntegerArray() {

        int[] actual = {1,4,6};
        int[] expected = {1,6,4};

        //Arrays.sort(expected);
        //Arrays.sort(actual);

        Assertions.assertArrayEquals( expected, actual);

        //Assertions.assertArrayEquals(expected, actual,
        //		"Expected age does not match with actual age");


    }

    @Test
    public void testString() {

        String actual = "Iftekhar A Ivaan";
        String expected = "Ivaanx";

        //Assertions.assertTrue(actual.endsWith(expected));
        Assertions.assertTrue(actual.endsWith(expected),"Actual '"
                + actual +
                "' does not end with " + expected);

    }
    @Test
    public void testString2() {

        String actual = "Iftekhar A Ivaan";
        String expected = "Ivaan";

        //Assertions.assertTrue(actual.endsWith(expected));
        Assertions.assertTrue(actual.contains("A"),"Actual '"
                + actual +
                "' does contains 'A'");
    }

    @Test
    public void testString3() {

        String actual = "Iftekhar A Ivaan";
        String expected = "iftekhar A Ivaanx";

        //Assertions.assertEquals(expected,actual);

        //Assertions.assertEquals(expected.toUpperCase(),actual.toUpperCase());

        //Assertions.assertTrue(actual.equalsIgnoreCase(expected));

        Assertions.assertTrue(actual.equalsIgnoreCase(expected),"Actual '"
                + actual +
                "' does not match with expected '"
                + expected + "'" );
    }

    @Test
    public void testString4() {

        String actual = "Iftekhar A Ivaanx";

        //Assertions.assertTrue(actual.endsWith(expected));
        Assertions.assertTrue(actual.matches("^If.*A.*an$"),"Actual '"
                + actual +
                "' does not expected RegEx" );

    }

    @Test
    public void testString5() {

        String actual = "9sdsd9a";

        //Assertions.assertTrue(actual.endsWith(expected));
        Assertions.assertTrue(actual.matches("[0-9].*[0-9][A-Za-z]"),"Actual '"
                + actual +
                "' does not expected RegEx" );

    }

    @Test
    public void testRegexString1() {

        String actual = "9998";

        //Assertions.assertTrue(actual.endsWith(expected));
        Assertions.assertTrue(actual.matches("\\d+"),"Actual '"
                + actual +
                "' does not expected RegEx" );

    }

    @Test
    public void testRegexString2() {

        //String actual = "9998";
        String actual = "9998.5";

        //Assertions.assertTrue(actual.endsWith(expected));
        Assertions.assertTrue(actual.matches("\\d+(\\.\\d+)?"),"Actual '"
                + actual +
                "' does not expected RegEx" );


        //String test = "^.*[0-9][A-Za-z]\\d+$";

    }


}
