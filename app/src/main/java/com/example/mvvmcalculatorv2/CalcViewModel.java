package com.example.mvvmcalculatorv2;


import androidx.lifecycle.ViewModel;

public class CalcViewModel extends ViewModel {


    private Calculator calculator;
    private double result;


    public double getResult(String equation){
        this.calculator = new Calculator(equation);
        this.result = calculator.getCalculationResult();
        return this.result;
    }


}
