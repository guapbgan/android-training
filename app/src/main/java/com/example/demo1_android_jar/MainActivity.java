package com.example.demo1_android_jar;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.demo1_android_jar.databinding.ActivityMain2Binding;
import com.example.demo1_android_jar.databinding.ActivityMainBinding;
import com.example.demolib.LibraryMain;

public class MainActivity extends Activity {
    private ActivityMainBinding binding;
    private ActivityMain2Binding binding2; //another ActivityMain
    private static final String LOG_TAG = "demo1_java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //new version setContentView: initial view binding, and assign
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //old version setContentView
//        setContentView(R.layout.activity_main);

        int result = LibraryMain.add(3, 5);
        binding.textView1.setText(String.format("!!result=%d", result)); //with view binding
        String[] dayOfWeek = getResources().getStringArray(R.array.dayOfWeek);
        StringBuilder builder = new StringBuilder();
        for(String d: dayOfWeek){
            builder.append(d);
            builder.append("\n");
            Log.v(LOG_TAG, d);
            Log.d(LOG_TAG, d);
            Log.i(LOG_TAG, d);
            Log.w(LOG_TAG, d);
            Log.e(LOG_TAG, d);
        }
        binding.textView1.setText(builder.toString());

//        TextView textView = findViewById(R.id.textView1); //without view binding
//        textView.setText(String.format("!!result=%d", result)); //without view binding
    }

    public void doActivity(View view){
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id1", String.format("%d", System.currentTimeMillis()));
        intent.putExtras(bundle);
//        intent.putExtra("id1", "adf");
        startActivity(intent);

    }
    public void doCall(View view){
        checkPermission();
    }

    public void doYahoo(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.yahoo.com.tw"));
        startActivity(intent);
    }


    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //do more stuff
//            TextView textView = findViewById(R.id.textView1);
//            textView.setText("We need to prompt user to open permission");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                showPromptDialog();
            }else {
                askForPermission();
            }
        }else{
            dialNumber();
        }
    }



    private static final int CALL_PHONE_CHECK = 1234;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CALL_PHONE_CHECK:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    dialNumber();
                }else {
                    binding.textView1.setText("if not grant, we can not proceed"); //with view binding

//                    TextView textView = findViewById(R.id.textView1);//without view binding
//                    textView.setText("if not grant, we can not proceed");
                }
        }
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_CHECK);
    }
    private void showPromptDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Need call phone permission")
                .setMessage("for any reason")
                .setPositiveButton("OK", (d, w)->askForPermission()) //one line style
                .setNegativeButton("NO", (d, w)->{ //multiple line style
                    finish();
                })
                .show();
    }

    private void dialNumber() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:021234567"));
        startActivity(intent);
    }
}