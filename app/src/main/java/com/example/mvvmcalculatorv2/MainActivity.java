package com.example.mvvmcalculatorv2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmcalculatorv2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener , EqualListener {

     private ActivityMainBinding binding;
     private CalcViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
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
        binding.btnClear.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
//        binding.btnRightBracket.setOnClickListener(this);
//        binding.btnLeftBracket.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(CalcViewModel.class);
        binding.setViewmodel(viewModel);
        binding.executePendingBindings();
        viewModel.listener = this;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        for (int i = 0; i <= 9; i++){
            String btns = "btn_"+i;
            int id = getResources().getIdentifier(btns,"id",getPackageName());
            if (view.getId() == id){
                binding.tvOperation.setText(binding.tvOperation.getText().toString() + i +"");
            }
        }
        if (binding.tvOperation.getText().toString().equals(""))
            return;

        switch (view.getId()){
            case R.id.btn_add:
                if (!viewModel.lastIsOp())
                binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.ADDITION_OP);
                else
                    viewModel.changeLastOp(Operation.ADDITION_OP);
                break;
            case R.id.btn_sub:
                if (!viewModel.lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.SUBTRACTION_OP);
                else
                    viewModel.changeLastOp(Operation.SUBTRACTION_OP);
                break;
            case R.id.btn_mul:
                if (!viewModel.lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.MULTIPLICATION_OP);
                else
                    viewModel.changeLastOp(Operation.MULTIPLICATION_OP);
                break;
            case R.id.btn_div:
                if (!viewModel.lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.DIVISION_OP);
                else
                    viewModel.changeLastOp(Operation.DIVISION_OP);
                break;
            case R.id.btn_dot:
                if (!viewModel.lastIsOp())
                    binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.DOT);
                else
                    viewModel.changeLastOp(Operation.DOT);
                break;
//            case R.id.btn_right_bracket:
//                binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.RIGHT_BRACKET);
//                break;
//            case R.id.btn_left_bracket:
//                binding.tvOperation.setText(binding.tvOperation.getText().toString() + Operation.LEFT_BRACKET);
//                break;
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_back:
                viewModel.back();
                break;

        }
    }

    // handle the equal button
    @Override
    public void onEqualClick(double result) {
        binding.tvResult.setText(String.valueOf(result));
    }

    // Detect any miss type and fix it
    @Override
    public void onTextChange(String newText) {
        binding.tvOperation.setText(newText);
    }

    // Clear fields
    public void clear(){
        binding.tvOperation.setText("");
        binding.tvResult.setText("");
    }




}