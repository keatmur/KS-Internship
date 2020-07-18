package com.example.ks_internship.app.activity;

import com.example.ks_internship.app.base.BaseActivity;

import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ks_internship.R;
import com.example.ks_internship.app.fragment.ViewFragment;
import com.example.ks_internship.app.utils.Constants;


public class ForResulttActivity extends BaseActivity {

    private ViewFragment viewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);
        initToolbarWithNavigation(getString(R.string.app_name));

        String text = getIntent().getStringExtra(Constants.KEY_RES_ID);
        viewFragment = (ViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet_view);
        viewFragment.displayResource(text);


    }


}