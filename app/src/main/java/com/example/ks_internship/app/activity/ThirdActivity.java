package com.example.ks_internship.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ks_internship.R;
import com.example.ks_internship.app.base.BaseActivity;
import com.example.ks_internship.app.model.Song;
import com.example.ks_internship.app.utils.Constants;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import androidx.appcompat.widget.AppCompatButton;

public class ThirdActivity extends BaseActivity {

    private TextInputLayout name;
    private TextInputLayout singer;
    private TextInputLayout gener;
    private TextInputLayout alibom;
    private AppCompatButton inputBtn;
    private Song song;
    private int position =-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initToolbarWithNavigation(getString(R.string.third_activity_title));
        initInputLayout();
        setListneret();
        initEbitText();






    }
    public void initEbitText(){
        if (getIntent().getExtras() != null) {
            song =getIntent().getExtras().getParcelable(Constants.EXTRA_EBIT);

            name.getEditText().setText(song.getName());
            singer.getEditText().setText(song.getSinger());
            gener.getEditText().setText(song.getGenre());
            alibom.getEditText().setText(song.getAlibom());

            position = getIntent().getExtras().getInt(Constants.EXTRA_EBIT_POSITION);
        }


    }


    public void initInputLayout() {

        name = findViewById(R.id.nameSong_input);
        singer = findViewById(R.id.singerSong_input);
        gener = findViewById(R.id.genre_input);
        alibom = findViewById(R.id.alibom_input);
        inputBtn = findViewById(R.id.inputBtn);
    }

    public void setListneret() {
        inputBtn.setOnClickListener(v -> {

            if (getEbitTextNotNull()) {
                Intent intent = new Intent();
                intent.putExtra(Constants.EXTRA_MESSAGE, song);
                if(position>-1) intent.putExtra(Constants.EXTRA_EBIT_POSITION,position);
                setResult(RESULT_OK, intent);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    public  boolean getEbitTextNotNull(){
        String nameSong = Objects.requireNonNull(name.getEditText()).getText().toString().trim() ;
        String singerSong = Objects.requireNonNull(singer.getEditText()).getText().toString().trim() ;
        String generSong = gener.getEditText().getText().toString().trim() ;
        String alibomSong = alibom.getEditText().getText().toString().trim() ;

        if (nameSong.length() == 0) {
            name.setError("Fill in the Blank Field");
        } else {
            name.setError(null);
        }
        if (singerSong.length() == 0) {
            singer.setError("Fill in the Blank Field");
        } else {
            singer.setError(null);
        }
        if (generSong.length() == 0) {
            gener.setError("Fill in the Blank Field");
        } else {
            gener.setError(null);
        }
        if (alibomSong.length() == 0) {
            alibom.setError("Fill in the Blank Field");
        } else {
            alibom.setError(null);
        }
        if (nameSong.length() != 0 && singerSong.length() != 0 && generSong.length() != 0 && alibomSong.length() != 0) {

            song = new Song(nameSong, singerSong, generSong, alibomSong);
            return true;
        }
        return false;
    }





}