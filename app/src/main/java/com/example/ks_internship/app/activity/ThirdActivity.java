package com.example.ks_internship.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


import com.example.ks_internship.R;
import com.example.ks_internship.app.base.BaseActivity;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.adapter.SearcHistoryAdapter;
import com.example.ks_internship.app.utils.lisners.OnSearchHistoryListener;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private SearcHistoryAdapter adapter;
    private List<String> titles;
    private Intent intent;
    private OnSearchHistoryListener onSearchHistoryListener = (v, string) -> {

        intent.putExtra(Constants.EXTRA_SEARCH_HISTORY,string);
        setResult(RESULT_OK,intent);

        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        initToolbarWithNavigation(getString(R.string.third_activity_title));

        Gson gson = new Gson();
        String jsonText = SaveSearchHistory.getTitles(this);
        titles =  new ArrayList<>();
        if(!TextUtils.isEmpty(jsonText)) {
            titles.addAll(Arrays.asList(gson.fromJson(jsonText, String[].class)));
        }
        intent=new Intent();

        adapter=new SearcHistoryAdapter(titles);
        recyclerView =findViewById(R.id.rv_history_search);

        adapter.setListener(onSearchHistoryListener);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));
        recyclerView.setAdapter(adapter);






    }











}