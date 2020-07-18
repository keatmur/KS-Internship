package com.example.ks_internship.app.activity;

import com.example.ks_internship.app.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.ks_internship.R;
import com.example.ks_internship.app.fragment.ChoiceFragment;
import com.example.ks_internship.app.fragment.ThirdFragment;
import com.example.ks_internship.app.fragment.ViewFragment;
import com.example.ks_internship.app.model.Song;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.lisners.SongsSelectLisner;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends BaseActivity {

    private ChoiceFragment choiceFragment;
    private ViewFragment viewFragment;
    private AppCompatButton test;

    boolean isTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.app_name));

        test = findViewById(R.id.test_tab);
        test.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);

        });

        isTwoPane = findViewById(R.id.fragmet_view) != null;

        choiceFragment = (ChoiceFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet_choice);
        if (isTwoPane) {
            viewFragment = (ViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragmet_view);
        }

        SongsSelectLisner songsSelectLisner = new SongsSelectLisner() {
            @Override
            public void onSongOne() {
                Song song = new Song("Beautiful People", "Ed Sheeran ", " Pop music", "No.6 Collaborations Project");
                displaySelected(song.toString());
            }

            @Override
            public void onSongTwo() {
                Song song = new Song("Level of Concern",
                        "Twenty One Pilot", "Alternative music / indi", "Level of Concern");
                displaySelected(song.toString());
            }

            @Override
            public void onSongThree() {
                Song song = new Song("21 Guns",
                        "Green Day", "Alternative music / indi", "21st Century Breakdown");
                displaySelected(song.toString());
            }

            @Override
            public void onSongFour() {
                Song song = new Song("Viva La Vida", "Coldplay", "Pop music", "Viva La Vida (Prospekt's March Edition)");
                displaySelected(song.toString());
            }

            @Override
            public void onSoungFive() {
                Song song = new Song("Come As You Are ", "Nirvana", "Альтернативна музика/інді, Рок", "Nevermind");
                displaySelected(song.toString());
            }


        };

        choiceFragment.setSongsSelectLisner(songsSelectLisner);
    }

    private void displaySelected(String text) {
        if (isTwoPane) {
            viewFragment.displayResource(text);
        } else {
            Intent viewIntent = new Intent(MainActivity.this, ForResulttActivity.class);
            viewIntent.putExtra(Constants.KEY_RES_ID, text);
            startActivity(viewIntent);
        }

    }

}