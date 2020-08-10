package com.example.ks_internship.app.screens.historySearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ks_internship.R;
import com.example.ks_internship.app.activity.ThirdActivity;
import com.example.ks_internship.app.screens.choice.ChoiceContract;
import com.example.ks_internship.app.utils.Constants;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.adapter.SearcHistoryAdapter;
import com.example.ks_internship.app.utils.lisners.OnSearchHistoryListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class HistoryFragment extends Fragment implements HistoryContract.View {

    private RecyclerView recyclerView;
    private SearcHistoryAdapter adapter;
    private List<String> titles;
    private Intent intent;
    private HistoryContract.Presenter presenter;

    private OnSearchHistoryListener onSearchHistoryListener = (v, string) -> {

        intent.putExtra(Constants.EXTRA_SEARCH_HISTORY, string);
        getActivity().setResult(RESULT_OK, intent);

        getActivity().finish();
    };


    public HistoryFragment() {
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
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        intent = new Intent();

        adapter = new SearcHistoryAdapter(titles);
        recyclerView = v.findViewById(R.id.rv_history_search);

        adapter.setListener(onSearchHistoryListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        presenter.takeView(this);
        return v;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }


    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setArrayList(List<String> list) {
        adapter.setArrayList(list);
        adapter.notifyDataSetChanged();
    }

}