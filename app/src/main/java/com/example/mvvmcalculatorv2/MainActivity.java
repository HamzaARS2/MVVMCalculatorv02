package com.example.mvvmcalculatorv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmcalculatorv2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ActivityMainBinding binding;
    private ArrayList<Double> values;
    private ArrayList<Character> operations;
    private CalcViewModel viewModel;
    private String tempVal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        values = new ArrayList<>();
        operations = new ArrayList<>();
        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
        binding.btn7.setOnClickListener(this);
        binding.btn8.setOnClickListener(this);
        binding.btn9.setOnClickListener(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnSub.setOnClickListener(this);
        binding.btnMul.setOnClickListener(this);
        binding.btnDiv.setOnClickListener(this);
        binding.btnDot.setOnClickListener(this);
        binding.btnEqual.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(CalcViewModel.class);

    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i <= 9; i++){
            String btns = "btn_"+i;
            int id = getResources().getIdentifier(btns,"id",getPackageName());
            if (view.getId() == id){
                tempVal = tempVal.concat(i+"");
                binding.tvOperation.setText(binding.tvOperation.getText().toString() + i +"");
            }
        }
        if (binding.tvOperation.getText().toString().equals(""))
            return;

        switch (view.getId()){
            case R.id.btn_add:
                if (!lastIsOp())
                binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.ADDITION_OP);
                else
                    changeLastOp(Operation.ADDITION_OP);
                break;
            case R.id.btn_sub:
                if (!lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.SUBTRACTION_OP);
                else
                    changeLastOp(Operation.SUBTRACTION_OP);
                break;
            case R.id.btn_mul:
                if (!lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.MULTIPLICATION_OP);
                else
                    changeLastOp(Operation.MULTIPLICATION_OP);
                break;
            case R.id.btn_div:
                if (!lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.DIVISION_OP);
                else
                    changeLastOp(Operation.DIVISION_OP);
                break;
            case R.id.btn_dot:
                if (!lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.DOT);
                else
                    changeLastOp(Operation.DOT);
                break;
            case R.id.btn_equal:
                if (!lastIsOp())
                binding.tvResult.setText(String.valueOf(viewModel.getResult(binding.tvOperation.getText().toString()+"=")));
                else {
                    back();
                    binding.tvResult.setText(String.valueOf(viewModel.getResult(binding.tvOperation.getText().toString()+"=")));
                }
                break;
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_back:
                back();
                break;


        }


    }

    public void clear(){
        binding.tvOperation.setText("");
        binding.tvResult.setText("");
    }

    public void back(){
        String str = binding.tvOperation.getText().toString();
        if (str.length() >0) {
            String newText = str.substring(0, str.length() - 1);
            binding.tvOperation.setText(newText);
        }
    }

    public boolean lastIsOp(){
        String text = binding.tvOperation.getText().toString();
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

    public void changeLastOp(char operation){
        String text = binding.tvOperation.getText().toString();
        String newText = text.substring(0,text.length()-1);
        newText = newText.concat(String.valueOf(operation));
        binding.tvOperation.setText(newText);
    }

}