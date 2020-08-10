package com.example.ks_internship.app.activity;

import com.example.ks_internship.app.base.BaseActivity;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.FrameLayout;


import com.example.ks_internship.R;
import com.example.ks_internship.app.screens.choice.ChoiceFragment;
import com.example.ks_internship.app.fragment.ViewFragment;
import com.example.ks_internship.app.model.DeezerTrack;
import com.example.ks_internship.app.screens.choice.ChoicePresenter;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.lisners.OnSongListener;


public class MainActivity extends BaseActivity {

    private ChoiceFragment choiceFragment;
    private ViewFragment viewFragment;
    private FrameLayout frameLayout;


    boolean isTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.main_activity_title));

        frameLayout = findViewById(R.id.fragmet_choice);

        choiceFragment = (ChoiceFragment) getSupportFragmentManager().findFragmentByTag("tag");
        if (choiceFragment == null) {
            choiceFragment = new ChoiceFragment();
        }
        choiceFragment.setPresenter(new ChoicePresenter(getDatabase(), new SaveSearchHistory(MainActivity.this)));
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), choiceFragment, "tag").commit();


        isTwoPane = findViewById(R.id.fragmet_view) != null;

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