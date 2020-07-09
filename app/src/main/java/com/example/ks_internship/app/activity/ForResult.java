package com.example.ks_internship.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ks_internship.R;
import com.example.ks_internship.app.utils.Constants;

public class ForResult extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);


    }
}