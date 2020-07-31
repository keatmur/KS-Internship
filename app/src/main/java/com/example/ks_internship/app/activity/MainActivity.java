package com.example.ks_internship.app.activity;

import com.example.ks_internship.app.base.BaseActivity;


import android.content.Intent;
import android.os.Bundle;


import com.example.ks_internship.R;
import com.example.ks_internship.app.fragment.ChoiceFragment;
import com.example.ks_internship.app.fragment.ViewFragment;
import com.example.ks_internship.app.model.DeezerTrack;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.lisners.OnSongListener;


public class MainActivity extends BaseActivity {

    private ChoiceFragment choiceFragment;
    private ViewFragment viewFragment;


    boolean isTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.main_activity_title));


        isTwoPane = findViewById(R.id.fragmet_view) != null;

        choiceFragment = (ChoiceFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet_choice);
        if (isTwoPane) {
            viewFragment = (ViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet_view);
        }

        OnSongListener onSongListener = new OnSongListener() {
            @Override
            public void setSong(DeezerTrack track) {
                displaySelected(track);
            }
        };


        choiceFragment.setOnSongListener(onSongListener);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RESULT_COD) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    choiceFragment.setResult(data.getStringExtra(Constants.EXTRA_SEARCH_HISTORY));
                }
            }
            }
        }



    private void displaySelected(DeezerTrack track) {
        if (isTwoPane) {
            viewFragment.displayResource(track);
        } else {
            Intent viewIntent = new Intent(MainActivity.this, SecondActivity.class);
            viewIntent.putExtra(Constants.KEY_RES_ID, track);
            startActivity(viewIntent);
        }

    }

}