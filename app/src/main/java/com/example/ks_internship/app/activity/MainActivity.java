package com.example.ks_internship.app.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.ks_internship.R;
import com.example.ks_internship.app.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton button_input;
    private AppCompatEditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        clicListeners();
    }

    private void initViews() {
        button_input = findViewById(R.id.button_input);
        input = findViewById(R.id.input);
    }

    private void clicListeners() {
        button_input.setOnClickListener(v -> {
            if (TextUtils.isEmpty(input.getText())) {
                Toast.makeText(MainActivity.this, "EROR", Toast.LENGTH_LONG).show();
            } else {
                openActivityForResult();
            }
        });
    }

    private void openActivityForResult() {
        Intent intent = new Intent(MainActivity.this, ForResult.class);
        //intent.putExtra(Constants.EXTRA_MESSAGE, input.getText().toString());
        startActivityForResult(intent,Constants.RESULT_COD);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RESULT_COD) {
            if (data != null) {
                if (data.getExtras() != null) {

                }
            }
        }
    }



}