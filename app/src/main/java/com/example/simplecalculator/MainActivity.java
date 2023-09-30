package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result;
    double previous_value = 0;
    String inputing = "";
    String operator = "";
    double[] twoinputs =new double[3]; //[2]is pointer, 1: please input first, 2: please input second, 3:full


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twoinputs[2] = 1;
        result = findViewById(R.id.result);
        //one
        Button one = findViewById(R.id.one);
        one.setOnClickListener(this);
        //two
        Button two = findViewById(R.id.two);
        two.setOnClickListener(this);
        //three
        Button three = findViewById(R.id.three);
        three.setOnClickListener(this);
        //four
        Button four = findViewById(R.id.four);
        four.setOnClickListener(this);
        //five
        Button five = findViewById(R.id.five);
        five.setOnClickListener(this);
        //six
        Button six = findViewById(R.id.six);
        six.setOnClickListener(this);
        //seven
        Button seven = findViewById(R.id.seven);
        seven.setOnClickListener(this);
        //eight
        Button eight = findViewById(R.id.eight);
        eight.setOnClickListener(this);
        //nine
        Button nine = findViewById(R.id.nine);
        nine.setOnClickListener(this);
        //zero
        Button zero = findViewById(R.id.zero);
        zero.setOnClickListener(this);
        //pluse
        Button pluse = findViewById(R.id.pluse);
        pluse.setOnClickListener(v -> {
            manage_input();
            double rxx = evaluate(operator);
            twoinputs[0]=rxx;
            twoinputs[2]=2;
            result.setText(String.format("%s+",num_to_string(rxx)));
            operator="+";});
        //minues
        Button minues = findViewById(R.id.minues);
        minues.setOnClickListener(v -> {
            manage_input();
            double rxx = evaluate(operator);
            twoinputs[0]=rxx;
            twoinputs[2]=2;
            result.setText(String.format("%s-",num_to_string(rxx)));
            operator="-";});
        //multiply
        Button multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(v -> {
            manage_input();
            double rxx = evaluate(operator);
            twoinputs[0]=rxx;
            twoinputs[2]=2;
            result.setText(String.format("%s×",num_to_string(rxx)));
            operator="×";});
        //divide
        Button divide = findViewById(R.id.divide);
        divide.setOnClickListener(v -> {
            manage_input();
            double rxx = evaluate(operator);
            twoinputs[0]=rxx;
            twoinputs[2]=2;
            result.setText(String.format("%s÷",num_to_string(rxx)));
            operator="÷";});
        //prev
        Button prev = findViewById(R.id.previous);
        prev.setOnClickListener(v -> {inputing = String.format("%s%s",inputing,num_to_string(previous_value));
            result.setText(String.format("%s%s",result.getText(),num_to_string(previous_value)));});
        //dot
        Button dot = findViewById(R.id.dot);
        dot.setOnClickListener(this);
        //equal
        Button equal = findViewById(R.id.equal);
        equal.setOnClickListener(v -> {
            manage_input();
            previous_value = evaluate(operator);
            result.setText(num_to_string(previous_value));
            inputing="";
            operator="";
            inputing = num_to_string(previous_value);
            twoinputs[2]=1;});
        //power
        Button power = findViewById(R.id.power);
        power.setOnClickListener(v -> {
            manage_input();
            double rxx = evaluate(operator);
            twoinputs[0]=rxx;
            twoinputs[2]=2;
            result.setText(String.format("%s^",num_to_string(rxx)));
            operator="^";});
        //clear all
        Button clear_all = findViewById(R.id.clear_all);
        clear_all.setOnClickListener(v -> {result.setText("");inputing="";operator="";twoinputs[2]=1;});
        //backspace
        Button backspace = findViewById(R.id.backspace);
        backspace.setOnClickListener(v -> {result.setText(result.getText().toString().substring(0,result.getText().toString().length()-1));
            if (inputing.equals("")) {
                twoinputs[2]=1;
                inputing = num_to_string(twoinputs[0]);
                operator="";
            } else {
                //int ptr = (int)twoinputs[2];
                inputing = inputing.substring(0,inputing.length()-1);
            }});

    }
    public void manage_input() {
        String[] token = inputing.split("\\.");
        int ptr = (int)twoinputs[2]-1;
        if (token.length == 1) {
            twoinputs[ptr]=Integer.parseInt(token[0]);
        } else if (token.length == 0) {
            twoinputs[ptr]=0;
        } else {
            int integer_part = Integer.parseInt(token[0]);
            int decimal_part = Integer.parseInt(token[1]);
            double dec = decimal_part;
            while (dec>=1) {
                dec = dec/10.0;
            }
            double rst = integer_part + dec;
            twoinputs[ptr]=rst;
        }
        twoinputs[2]=ptr+2;
        inputing = "";
    }
    public String num_to_string (double num) {
        if (num % 1 ==0) {
            Integer temp = (int)num;
            return Integer.toString(temp);
        } else {
            return Double.toString(num);
        }
    }

    public double evaluate(String operator) {
        if (twoinputs[2]==1)
            return 0;
        if (twoinputs[2]==2)
            return twoinputs[0];
        double a = twoinputs[0];
        double b = twoinputs[1];
        if (operator.equals("+"))
            return a+b;
        if (operator.equals("-"))
            return a-b;
        if (operator.equals("×"))
            return a*b;
        if (operator.equals("÷")) {
            if (b==0)
                return 0;
            return a/b;
        }
        if (operator.equals("^"))
            return Math.pow(a,b);
        return 0;
    }

    @Override
    public void onClick(View v) {
        String number_inside = ((Button)v).getText().toString();
        inputing = String.format("%s%s",inputing,number_inside);
        String now_text = String.format("%s%s",result.getText(),number_inside);
        result.setText(now_text);
    }
}