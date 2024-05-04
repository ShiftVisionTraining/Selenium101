package com.shiftvision.qa.testcase.assertation;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// https://assertj.github.io/doc/#assertj-core-quick-start
public class AssertJAssertionTest {

    @Test
    public void test1(){
        String myName = "Iftekhar A Ivaan";
        assertThat(myName).isEqualTo("Iftekhar A Ivaan");
    }
    @Test
    public void test2(){
        int myAge = 49;
        assertThat(myAge).isEqualTo(49);
    }
    @Test
    public void test3(){
        String myName = "Iftekhar A Ivaan";
        assertThat(myName).startsWith("If")
                .endsWith("an")
                .contains("A")
                .contains("Iv");
    }

    @Test
    public void test4(){
        String myName = "Iftekhar A Ivaan";
        assertThat(myName).isEqualTo("Iftekhar A Ivaan")
                .isNotEqualTo("Simrohn");
    }

    @Test
    public void test4_2(){
        String myName = "Iftekhar A Ivaan";
        assertThat(myName).isNotEqualTo("Simrohn");
    }

    @Test
    public void test5(){
        String myName = "Iftekhar a Ivaan";
        assertThat(myName).isEqualToIgnoringCase("iftekhar A Ivaan");

    }
    @Test
    public void test6(){
        String myName = " Iftekhar    A Ivaan ";
        assertThat(myName).isEqualToIgnoringWhitespace(" Iftekhar A Ivaan");
    }
    @Test
    public void test7(){
        String myName = "Iftekhar A Ivaan\n";
        assertThat(myName).isEqualToIgnoringNewLines("Iftekhar A Ivaan");
    }
    @Test
    public void test8(){
        String myName = "Iftekhar A Ivaan";
        assertThat(myName).matches("^If.*aan$");
    }

    @Test
    public void test9(){
        String myName = "Iftekhar A Ivaan";
        assertThat(myName).matches(x -> x.startsWith("Ift"));
    }

    @Test
    public void test10(){
        String myName = "Iftekhar A Ivaanx";
        assertThat(myName).as("Verify Name").matches("^If.*aan$");
        //assertThat(myName).matches("^If.*aan$").as("Verify Name");
    }

    @Test
    public void test11(){
        String myName = null;
        assertThat(myName).isNull();
        //assertThat(myName).isNotNull();
        //assertThat(myName).isNotNull().startsWith("Iv");
    }
    @Test
    public void test12(){
        String myName = "";
        assertThat(myName).isEmpty();
        //assertThat(myName).isNotNull();
        //assertThat(myName).isNotNull().startsWith("Iv");
    }
    @Test
    public void test13(){
        String myName = null;
        assertThat(myName).isNullOrEmpty();
    }

    @Test
    public void test14(){
        double myAge = 45.8;
        assertThat(myAge).isEqualTo(45.8);
    }
    @Test
    public void test15(){
        float myAge = 45.8f;
        assertThat(myAge).isEqualTo(45.8f);
    }
    @Test
    public void test16(){
        String myAge = "45";
        assertThat(myAge).isEqualTo("45");
    }

    @Test
    public void test17(){
        String myAge = "46";
        int myAgex = 46;

        //assertThat(myAge).isGreaterThanOrEqualTo(45);
        assertThat(Integer.parseInt(myAge)).isGreaterThanOrEqualTo(45);
        assertThat(myAge).isEqualTo(myAgex + "");
    }

    @Test
    public void test18(){
        String myAge = "46.45459";
        assertThat(Float.parseFloat(myAge)).isEqualTo(46.45453f,offset(.01f));
        //assertThat(Float.parseFloat(myAge)).isEqualTo(46.45453f,offset(.0f));
    }


    @Test
    public void test19(){
        List<String> names = new ArrayList<>();
        names.add("Iftekhar");
        names.add("Shehla");
        names.add("Ameera");
        names.add("Simrohn");

        assertThat(names).hasSize(4)
                .containsExactly("Iftekhar","Shehla","Ameera","Simrohn");
    }

    @Test
    public void test20(){
        List<String> names = new ArrayList<>();
        names.add("Iftekhar");
        names.add("Shehla");
        names.add("Ameera");
        names.add("Simrohn");

        assertThat(names).hasSize(4)
                .containsExactlyInAnyOrder("Shehla","Ameera","Simrohn","Iftekhar");
    }

    @Test
    public void test21(){
        List<String> names = new ArrayList<>();
        names.add("Iftekhar");
        names.add("Shehla");
        names.add("Ameera");
        names.add("Simrohn");

        assertThat(names).hasSize(4)
                .containsAnyOf("Shehla","Iftekhar");
    }

    @Test
    public void test22(){
        List<String> names = new ArrayList<>();
        names.add("Iftekhar");
        names.add("Shehla");

        assertThat(names).containsAnyOf("Shehla","Iftekhar","Ameera","Simrohn");
    }

    @Test
    public void test23(){
        List<String> names = new ArrayList<>();
        names.add("Iftekhar");
        names.add("Shehla");

        List<String> namesExpected = new ArrayList<>();
        namesExpected.add("Iftekhar");
        namesExpected.add("Shehla");
        namesExpected.add("Ameera");
        namesExpected.add("Simrohn");

        assertThat(names).containsAnyOf(namesExpected.toArray(new String[namesExpected.size()]));
    }
    @Test
    public void test24(){
        String[] names = {"Iftekhar", "Shehla"};

        assertThat(names).containsExactly("Iftekhar", "Shehla");
    }

    @Test
    public void test25(){
        String[] names = {"Iftekhar", "Shehla"};
        String[] namesExpected = {"Iftekhar", "Shehla"};

        assertThat(names).containsExactly(namesExpected);
    }

    @Test
    public void test26(){
        String name  = "Iftekhar";
        int age = 49;
        double sale = 567.67;

        assertThat(name).isEqualTo("Iftekhar");
        assertThat(age).isEqualTo(50);
        assertThat(sale).isEqualTo(568.67);

    }

    @Test
    public void test28(){
        String name  = "Iftekharx";
        int age = 50;
        double sale = 568.67;

        SoftAssertions assertions = new SoftAssertions();

        assertions.assertThat(name).isEqualTo("Iftekhar");


        assertions.assertThat(age).isEqualTo(49);


        assertions.assertThat(sale).isEqualTo(567.67);

        assertions.assertAll();
    }

}
