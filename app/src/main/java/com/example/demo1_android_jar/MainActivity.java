package com.example.demo1_android_jar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demolib.LibraryMain;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int result = LibraryMain.add(3, 5);
        TextView textView = findViewById(R.id.textView1);
        textView.setText(String.format("!!result=%d", result));
    }

    public void doActivity(View view){
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);

    }
}