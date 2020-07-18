package com.example.ks_internship.app.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.app.utils.lisners.SongsSelectLisner;

public class ChoiceFragment extends Fragment {

public SongsSelectLisner songsSelectLisner;

    public AppCompatButton songOne;
    public AppCompatButton songTwo;
    public AppCompatButton songThree;
    public AppCompatButton songFour;
    public AppCompatButton songFive;

    public ChoiceFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choice, container, false);
        songOne= v.findViewById(R.id.song_1);
        songTwo= v.findViewById(R.id.song_2);
        songThree= v.findViewById(R.id.song_3);
        songFour= v.findViewById(R.id.song_4);
        songFive= v.findViewById(R.id.song_5);

        songOne.setOnClickListener(view -> {
            if (songsSelectLisner != null) {
                songsSelectLisner.onSongOne();
            }
        });

        songTwo.setOnClickListener(view -> {
            if (songsSelectLisner != null) {
                songsSelectLisner.onSongTwo();
            }
        });

        songThree.setOnClickListener(view -> {
            if (songsSelectLisner != null) {
                songsSelectLisner.onSongThree();
            }
        });

        songFour.setOnClickListener(view -> {
            if (songsSelectLisner != null) {
                songsSelectLisner.onSongFour();
            }
        });

        songFive.setOnClickListener(view -> {
            if (songsSelectLisner != null) {
                songsSelectLisner.onSoungFive();
            }
        });
        return v;
    }

    public void setSongsSelectLisner(SongsSelectLisner songsSelectLisner) {
        this.songsSelectLisner = songsSelectLisner;
    }
}