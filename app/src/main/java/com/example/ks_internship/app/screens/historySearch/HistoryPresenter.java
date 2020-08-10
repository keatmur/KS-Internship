package com.example.ks_internship.app.screens.historySearch;

import android.text.TextUtils;

import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter {
    SaveSearchHistory searchHistory;
    Gson gson = new Gson();
    List<String> list;
    HistoryContract.View view;


    public HistoryPresenter(SaveSearchHistory searchHistory) {
        this.searchHistory = searchHistory;
    }


    @Override
    public void takeView(HistoryContract.View view) {

        this.view = view;
        gson = new Gson();
        String jsonText = searchHistory.getTitles();
        list = new ArrayList<>();
        if (!TextUtils.isEmpty(jsonText)) {
            list.addAll(Arrays.asList(gson.fromJson(jsonText, String[].class)));
        }
        view.setArrayList(list);
    }

    @Override
    public void dropView() {
        this.view = null;
        list = null;

    }
}
