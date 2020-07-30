package com.example.ks_internship.app.activity;

import com.example.ks_internship.app.base.BaseActivity;

import android.os.Bundle;

import com.example.ks_internship.R;
import com.example.ks_internship.app.fragment.ViewFragment;
import com.example.ks_internship.app.model.DeezerTrack;
import com.example.ks_internship.app.utils.Constants;


public class SecondActivity extends BaseActivity {

    private ViewFragment viewFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initToolbarWithNavigation(getString(R.string.secoond_activity_title));

        DeezerTrack track = getIntent().getParcelableExtra(Constants.KEY_RES_ID);
        viewFragment = (ViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet_view);
        viewFragment.displayResource(track);


    }


}