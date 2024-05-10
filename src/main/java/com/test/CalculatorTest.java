package com.test;

import com.cal.Calculator;
import com.calc.service.CalService;
import com.calc.service.CalServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    private static Calculator calc = null;
    private static CalService calService = null;

    @BeforeAll
    public static void initTest(){
        calc = new Calculator();
        calService = new CalServiceImpl();
    }
    @AfterAll
    public static void afterTest(){
        calc = null;
    }

    @Test
    @DisplayName("Test add method")
    @Tag("Math") //Run only those test method having specific tag, using edit run configuration by right clicking on project.
    public void testAdd(){
        int a = 10;
        int b = 20;
        int c = a+b;
        assertEquals(c, calc.add(a,b));
    }

    @Test
    @DisplayName("Test mul method")
    @Tag("Math")
    //@Disabled // used to disable this test case
    public void testMul(){
        int a = 10;
        int b = 20;
        int c = a*b;
        int output = calc.mul(a,b);
        assertEquals(c, output, ()->"Multiplication of "+a+"*"+b+"="+output+" but expected = "+c);
        //why used ()-> lambda expression in showwing error, because this string concatination will
        // create 8 objuect so using lamda expression it will execute only when failed case occoures
    }

    @Test    //(expected = ArithmeticException.class) this was supported in older version of junit
    @DisplayName("Test div method")
    @Tag("Math")
    public void testDiv(){
        int a = 10;
        int b = 20;
        int c = a/b;
        int output = calc.div(a,b);

        assertThrows(ArithmeticException.class, ()->calc.div(a,0));
        assertEquals(c, output, ()->"Division of "+a+"/"+b+"="+output+" but expected = "+c);
        //why used ()-> lambda expression in showwing error, because this string concatination will
        // create 8 objuect so using lamda expression it will execute only when failed case occoures
    }

    @Test
    @DisplayName("Test windows method")
    @EnabledOnOs(OS.WINDOWS)
    //@EnabledOnJre(JRE.JAVA_21)
    @EnabledForJreRange(min=JRE.JAVA_8, max = JRE.JAVA_21)
    @Tag("OS")
    public void testWindow(){
        System.out.println("Execute for windows only");
    }

    @Test
    @DisplayName("Test linux method")
    @EnabledOnOs(value = {OS.LINUX, OS.MAC})
    @EnabledOnJre(JRE.JAVA_8)
    @Tag("OS")
    //    @EnabledOnOs(OS.LINUX)
    public void testLinux(){
        System.out.println("Execute for linux only");
    }

    @Test
    @DisplayName("Test executeIfConditionIsTrueInRuntime method")
    @Tag("isEnabledToExecute")//Run only those test method having specific tag, using edit run configuration by right clicking on project.
    public void executeIfConditionIsTrueInRuntime(){
        assumeTrue(calc.isEnabledToExecute); // is is used to check if value is true then only execute
        // the below statements else stop the execution in this method
        System.out.println("executeIfConditionIsTrueInRuntime is executed");
        assertEquals(true, true); //this is used to check if test result is matched or not
    }


    @Test
    @AfterEach
    @Disabled
    public void executeAfterEachTestCase(){
        System.out.println("execute After Each Test Case method executed");
    }

    @Test
    @AfterAll
    @Disabled
    public void executeAfterAllTestCase(){
        System.out.println("execute After All Test Case method executed");
    }

    @Test
    public void test_One_TestCase_MultipleTimes(){

        //in this situation if any one asserEquals fails then further executiin will also stop
         //       so to solve this we will use assertAll \


        assertAll(
                ()->assertEquals(10, calc.mul(2,5)),
                ()->assertEquals(12, calc.mul(2,6)),
                ()->assertEquals(14, calc.mul(2,7)),
                ()->assertEquals(16, calc.mul(2,8)),
                ()->assertEquals(18, calc.mul(2,9)),
                ()->assertEquals(20, calc.mul(2,10)),
                ()-> System.out.println("test_One_TestCase_MultipleTimes Completed")
        );

    }


    //1. Test method with void return type
    //2. I want to test how many times this method is called
    //3. How to test an interface abstract method

    @Test
    public void testVoidMethod(){
        calc.voidMethod(3);
        assertEquals(calc.isEnabledToExecute, true, ()->"after calling this method the value of isEnabledToExecute should be true");
    }

    @Test
    public void test_how_many_times_method_is_called(){
        calc.count = 0;
        calc.voidMethod(2);
        calc.voidMethod(4);
        calc.voidMethod(5);

        assertAll(
                ()->assertTrue(()->calc.count > 0?true:false, ()->"voidMethod called "+calc.count+" times"),
                ()->assertEquals(calc.count, 3)
        );
    }

    @Test
    public void test_AbstractClass(){

        String countryCode = calService.getCountryCode("India");
        String pinCode = calService.getPinCode("Bhandup");

        assertAll(
                ()->assertEquals(countryCode, "+91"),
                ()->assertEquals(pinCode, "400078")
        );
    }

}
