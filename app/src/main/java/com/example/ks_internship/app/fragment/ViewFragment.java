package com.example.ks_internship.app.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;

public class ViewFragment extends Fragment {
    AppCompatTextView aboutSong;

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
        aboutSong = v.findViewById(R.id.textView_aboutSong);
        return v;
    }

    public void displayResource(String text) {
        aboutSong.setText(text);
    }

}