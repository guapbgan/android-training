package com.example.demo1_android_jar;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second);
    }
}
