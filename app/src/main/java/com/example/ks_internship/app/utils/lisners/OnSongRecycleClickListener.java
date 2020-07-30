package com.example.ks_internship.app.utils.lisners;

import android.view.View;

public interface OnSongRecycleClickListener {
    public void onItemClick(View v, int position);
    public void onItemDeleteClick(View v, int position);
    public void onItemShareClick(View v, int position);


}

