package com.example.mvvmcalculatorv2;




import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalcViewModel extends ViewModel {


    private Calculator calculator;
    private double finalResult;
    public EqualListener listener;
    private MutableLiveData<String> _equation = new MutableLiveData<>();
    public LiveData<String> equation = null;


    public double getResult(String equation){
        this.calculator = new Calculator(equation);
        this.finalResult = calculator.getCalculationResult();
        return this.finalResult;
    }


    public void onEqualClick(){
        if (lastIsOp()) {
            back();
            listener.onTextChange(_equation.getValue());
        }
        listener.onEqualClick(getResult(_equation.getValue()+"="));
    }

    public LiveData<String> getEquation() {
        return equation;
    }

    public void setEquation(Editable equation) {
        _equation.setValue(equation.toString());
    }

    public boolean lastIsOp(){
        String text = _equation.getValue();
        switch (text.charAt(text.length()-1)){
            case Operation.ADDITION_OP:
            case Operation.SUBTRACTION_OP:
            case Operation.MULTIPLICATION_OP:
            case Operation.DIVISION_OP:
            case Operation.DOT:
                return true;
        }
        return false;
    }

    public void back(){
        String str = _equation.getValue();
        if (str.length() >0) {
            String newText = str.substring(0, str.length() - 1);
            _equation.setValue(newText);
            listener.onTextChange(_equation.getValue());
        }
    }

    public void changeLastOp(char operation) {
        String text = _equation.getValue();
        String newText = text.substring(0, text.length() - 1);
        newText = newText.concat(String.valueOf(operation));
        _equation.setValue(newText);
        listener.onTextChange(_equation.getValue());
    }
}
