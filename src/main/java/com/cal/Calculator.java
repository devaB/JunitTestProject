package com.cal;

public class Calculator {

    public boolean isEnabledToExecute = false;
    public int count = 0;

    public int add(int a, int b){
        isEnabledToExecute = true;
        return a+b;
    }
    public int mul(int a, int b){
        return a*b;
    }
    public int sub(int a, int b){
        return a-b;
    }
    public int div(int a, int b) throws ArithmeticException{
        return a/b;
    }
    public void voidMethod(int a){
        System.out.println("voidMethod executed");
        isEnabledToExecute = true;
        count++;
        if(a % 2 == 0) isEnabledToExecute = true;
    }
}
