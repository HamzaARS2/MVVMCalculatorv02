package com.example.mvvmcalculatorv2;

import android.graphics.Path;

import java.util.ArrayList;


public class Calculator {


    private String equation;
    private double result = 0.0d;


    public Calculator(String equation){
     this.equation = equation;
    }



    public double getCalculationResult(){
        return getResult(equation);
    }

    private double getResult(String equation){

        char op = Operation.ADDITION_OP;
        String number = "";


        char[] eq = equation.toCharArray();
        for (int i = 0; i < eq.length; i++){
            switch (eq[i]){
                case Operation.ADDITION_OP:
                    result = getResultOf(result,Double.parseDouble(number),op);
                    op = Operation.ADDITION_OP;
                    number="";
                    break;
                case Operation.SUBTRACTION_OP:
                    result = getResultOf(result,Double.parseDouble(number),op);
                    op = Operation.SUBTRACTION_OP;
                    number="";
                    break;
                case Operation.MULTIPLICATION_OP:
                    result = getResultOf(result,Double.parseDouble(number),op);
                    op = Operation.MULTIPLICATION_OP;
                    number="";
                    break;
                case Operation.DIVISION_OP:
                    result = getResultOf(result,Double.parseDouble(number),op);
                    op = Operation.DIVISION_OP;
                    number="";
                    break;
                case Operation.DOT:

                    break;
                case Operation.EQUAL_OP:
                    if (!number.isEmpty())
                    result = getResultOf(result,Double.parseDouble(number),op);

                    return result;
                default:
                    number = number.concat(String.valueOf(eq[i]));
            }


        }

        return 0.0d;
    }

    private double getResultOf(double first , double second, char operation){
        switch (operation){
            case Operation.ADDITION_OP:
                return first + second;
            case Operation.SUBTRACTION_OP:
                return first - second;
            case Operation.MULTIPLICATION_OP:
                return first * second;
            case Operation.DIVISION_OP:
                return first / second;
            default:
                return 0.0d;
        }
    }

}
