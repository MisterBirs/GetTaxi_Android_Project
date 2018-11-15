package com.example.shlomi.gettaxi_android_project.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.shlomi.gettaxi_android_project.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void toPassengerActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
        startActivity(intent);
    }

}
