package com.calc.service;

public class CalServiceImpl extends CalService{

    @Override
    public String getPinCode(String street) {

        if(street.equals("Bhandup")){
            return "400078";
        }
        else if(street.equals("Mulund")){
            return "400076";
        }
        else{
            return "00";
        }
    }
}
