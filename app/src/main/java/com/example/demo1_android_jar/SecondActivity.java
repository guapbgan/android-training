package com.example.demo1_android_jar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends Activity implements View.OnClickListener {
    @BindView(R.id.button1)
    Button button1;

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second);

        //enable Butterknife
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        String id1String = bundle.getString("id1");

//        Button button1 = findViewById(R.id.button1);
        button1.setText(id1String);
        button1.setOnClickListener(this); //use clickListener method 1

//        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

//        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new InnerListener()); //use clickListener method 2

//        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new OuterListener());
//        button4.setOnClickListener(new OuterListener(this)); //another way

//        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(SecondActivity.this, "inner anonymous class clicked", Toast.LENGTH_LONG).show();
            }
        });

//        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener((v) -> {
            Toast.makeText(this, "lambda1 clicked", Toast.LENGTH_LONG).show();
        });

//        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            Toast.makeText(this, "lambda2 clicked", Toast.LENGTH_LONG).show();
        });

//        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> Toast.makeText(this, "lambda3 clicked", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onClick(View view) {
        String message = "";
        switch (view.getId()){
            case R.id.button1:
                message = "button1 clicked";
                break;
            case R.id.button2:
                message = "button2 clicked";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class InnerListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(SecondActivity.this, "[3]inner class callback clicked", Toast.LENGTH_LONG).show();
        }
    }
}