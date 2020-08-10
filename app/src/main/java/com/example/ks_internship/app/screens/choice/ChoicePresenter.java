package com.example.ks_internship.app.screens.choice;

import android.text.TextUtils;

import com.example.ks_internship.app.api.ApiCallback;
import com.example.ks_internship.app.api.RestClient;
import com.example.ks_internship.app.database.AppDatabase;
import com.example.ks_internship.app.model.DeezerRepoErrorItem;
import com.example.ks_internship.app.model.DeezerResponse;
import com.example.ks_internship.app.model.DeezerTrack;
import com.example.ks_internship.app.utils.KeyboardUtils;
import com.example.ks_internship.app.utils.SaveSearchHistory;
import com.example.ks_internship.app.utils.adapter.SongListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Response;

public class ChoicePresenter implements ChoiceContract.Presenter {

    ChoiceContract.View view;
    AppDatabase database;
    SaveSearchHistory searchHistory;
    LiveData<List<DeezerTrack>> liveData;

    private List<String> titleSearch;
    private DeezerResponse deezerResponse;
    private int nextCount;
    Gson gson;

    public ChoicePresenter(AppDatabase database, SaveSearchHistory searchHistory) {
        this.database = database;
        this.searchHistory = searchHistory;
    }

    @Override
    public void deleteTrack(DeezerTrack deezerTrack) {

        database.getPersonDao().deleteTrack(deezerTrack);

    }

    @Override
    public void search(String serch) {

        if (TextUtils.isEmpty(serch)) {
            view.showErorrInput();

        } else {
            view.hideKeyboard();
            loadRepos(serch);
            titleSearch.add(serch);
            searchHistory.setTitleSearch(gson.toJson(titleSearch));
        }
    }

    @Override
    public void loadRepos(String title) {
        view.showProgressBlock();
        RestClient.getsInstance().getService().getData(title).enqueue(new ApiCallback<DeezerResponse>() {
            @Override
            public void success(Response<DeezerResponse> response) {
                database.getPersonDao().deleteAllTracks();
                nextCount = 0;
                database.getPersonDao().insertAllTracks(response.body().getData());
                deezerResponse = response.body();
                view.hideProgressBlock();
                searchHistory.setInputSearch(title);
            }

            @Override
            public void failure(DeezerRepoErrorItem deezerRepoErrorItem) {
                if (TextUtils.isEmpty(deezerRepoErrorItem.getCode())) {
                    view.makeErrorToast("Error occurred during request: " + deezerRepoErrorItem.getMessage());
                } else {
                    view.makeErrorToast(deezerRepoErrorItem.getMessage() + "Code error:" + deezerRepoErrorItem.getCode());
                }

                view.hideProgressBlock();
            }


        });
    }

    @Override
    public void nextSearch() {

        String title = searchHistory.getInputSearch();
        if (deezerResponse != null) {
            if (!TextUtils.isEmpty(deezerResponse.getNext())) {
                nextCount = nextCount + 25;
                nextLoadRepos(title, nextCount);
            }
        }
    }

    public void nextLoadRepos(String string, int nextCount) {

        view.showProgressBlock();
        RestClient.getsInstance().getService().getData(string, nextCount).enqueue(new ApiCallback<DeezerResponse>() {
            @Override
            public void success(Response<DeezerResponse> response) {
                database.getPersonDao().insertAllTracks(response.body().getData());
                deezerResponse = response.body();
                view.hideProgressBlock();

            }

            @Override
            public void failure(DeezerRepoErrorItem deezerRepoErrorItem) {
                if (TextUtils.isEmpty(deezerRepoErrorItem.getCode())) {
                    view.makeErrorToast("Error occurred during request: " + deezerRepoErrorItem.getMessage());
                } else {
                    view.makeErrorToast(deezerRepoErrorItem.getMessage() + "Code error:" + deezerRepoErrorItem.getCode());
                }

                view.hideProgressBlock();
            }


        });
    }

    @Override
    public void takeView(ChoiceContract.View view) {
        this.view = view;
        gson = new Gson();
        titleSearch = new ArrayList<>();

        String jsonText = searchHistory.getTitles();
        if (!TextUtils.isEmpty(jsonText)) {
            titleSearch.addAll(Arrays.asList(gson.fromJson(jsonText, String[].class)));
        }
        nextCount = 0;

        view.setLostSearch(searchHistory.getInputSearch());
        liveData = database.getPersonDao().getAllTrackS();
        view.observeItems(liveData);

    }

    @Override
    public void dropView() {
        view.stopObserveItems(liveData);
        this.view = null;
        liveData = null;

    }
}
