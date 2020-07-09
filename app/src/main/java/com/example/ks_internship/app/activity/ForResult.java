package com.example.ks_internship.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ks_internship.R;
import com.example.ks_internship.app.utils.Constants;

public class ForResult extends AppCompatActivity {
    private AppCompatButton ok;
    private AppCompatButton cancel;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        initViews();
        setResult();
        setListeners();


    }

    private void initViews() {
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);
        text = findViewById(R.id.result_text);
    }

    private void setResult(){
        if (getIntent().getExtras() != null) {
            text.setText(getIntent().getStringExtra(Constants.EXTRA_MESSAGE));
        }
    }

    private void setListeners(){
        Intent intent = new Intent();
        ok.setOnClickListener(v->{
            setResult(RESULT_OK,intent);

            setResult(Activity.RESULT_OK,intent);
            finish();
        });
        cancel.setOnClickListener(v->{
            setResult(RESULT_CANCELED,intent);

            setResult(Activity.RESULT_CANCELED,intent);
            finish();
        });



    }

}