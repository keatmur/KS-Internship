package com.example.ks_internship.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;


import com.example.ks_internship.R;
import com.example.ks_internship.app.base.BaseActivity;
import com.example.ks_internship.app.screens.choice.ChoiceFragment;
import com.example.ks_internship.app.screens.choice.ChoicePresenter;
import com.example.ks_internship.app.screens.historySearch.HistoryFragment;
import com.example.ks_internship.app.screens.historySearch.HistoryPresenter;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.adapter.SearcHistoryAdapter;
import com.example.ks_internship.app.utils.lisners.OnSearchHistoryListener;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivity extends BaseActivity {

private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initToolbarWithNavigation(getString(R.string.third_activity_title));

        frameLayout=findViewById(R.id.fl_history_search);
        HistoryFragment historyFragment= new HistoryFragment();

        historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag("tag");
        if (historyFragment == null) {
            historyFragment = new HistoryFragment();
        }
        historyFragment.setPresenter(new HistoryPresenter( new SaveSearchHistory(ThirdActivity.this)));
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), historyFragment, "tag").commit();
    }











}