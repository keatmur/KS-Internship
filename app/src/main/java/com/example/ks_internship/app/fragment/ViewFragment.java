package com.example.ks_internship.app.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.example.ks_internship.R;
import com.example.ks_internship.app.model.DeezerTrack;

public class ViewFragment extends Fragment {
    AppCompatTextView title;
    AppCompatTextView albumTitle;
    AppCompatButton goDeezer;
    AppCompatImageView icCover;
    Intent intent;

    public ViewFragment() {
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
        View v = inflater.inflate(R.layout.fragment_view, container, false);
        title = v.findViewById(R.id.tv_titleArtist);
        albumTitle = v.findViewById(R.id.tv_albumTitle);
        goDeezer = v.findViewById(R.id.btn_go_dezeer);
        icCover = v.findViewById(R.id.ic_Cover);


        return v;
    }

    public void displayResource(DeezerTrack track) {

        title.setText(track.getArtist().getName() + " - " + track.getTitle());
        albumTitle.setText(track.getAlbum().getTitle());
        Glide.with(icCover).load(track.getAlbum().getCoverMedium()).placeholder(R.drawable.ic_music_note).into(icCover);
        goDeezer.setOnClickListener(v -> {
            intent = new Intent(Intent.ACTION_VIEW, track.getLink());
            startActivity(intent);
        });


    }
}