package com.example.demo1_android_jar;

import android.view.View;
import android.widget.Toast;

public class OuterListener implements View.OnClickListener {
//    private Context context; //another way
//    public OuterListener(SecondActivity secondActivity) {
//        context = secondActivity; //another way
//    }


    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "OuterListener is called", Toast.LENGTH_LONG).show();
//        Toast.makeText(context, "OuterListener is called", Toast.LENGTH_LONG).show(); //another way
    }
}
