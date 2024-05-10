package com.calc.service;

public abstract class CalService {

    public abstract String getPinCode(String street);

    public String getCountryCode(String country){
        if(country.equals("India")){
            return "+91";
        }
        else{
            return "00";
        }
    }
}
