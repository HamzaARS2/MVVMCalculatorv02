package com.example.mvvmcalculatorv2;

public class Numbers {


    private double firstNumber;
    private double SecondNumber;

    public Numbers() {
    }

    public Numbers(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        SecondNumber = secondNumber;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return SecondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        SecondNumber = secondNumber;
    }

}
