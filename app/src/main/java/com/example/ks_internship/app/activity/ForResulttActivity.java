package com.example.ks_internship.app.activity;

import com.example.ks_internship.app.base.BaseActivity;

import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ks_internship.R;
import com.example.ks_internship.app.utils.Constants;

public class ForResulttActivity extends BaseActivity {
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
        initToolbarWithNavigation(getString(R.string.secoond_activity_title));


    }

    private void initViews() {
        ok = findViewById(R.id.okBtn);
        cancel = findViewById(R.id.cancelBtn);
        text = findViewById(R.id.result_textView);
    }

    private void setResult() {
        if (getIntent().getExtras() != null) {
            text.setText(getIntent().getStringExtra(Constants.EXTRA_MESSAGE));
        }
    }

    private void setListeners() {
        Intent intent = new Intent();
        ok.setOnClickListener(v -> {
            setResult(RESULT_OK, intent);

            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        cancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED, intent);

            setResult(Activity.RESULT_CANCELED, intent);
            finish();
        });
    }

}