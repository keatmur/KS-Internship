package com.example.ks_internship.app.screens.choice;

import com.example.ks_internship.app.base.BasePresenter;
import com.example.ks_internship.app.base.BaseView;
import com.example.ks_internship.app.model.DeezerTrack;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface ChoiceContract {
    interface View extends BaseView<Presenter> {

        void makeErrorToast(String errorMessage);

        void showErorrInput();

        void observeItems(LiveData<List<DeezerTrack>> listLiveData);

        void stopObserveItems(LiveData<List<DeezerTrack>> listLiveData);

        void showProgressBlock();

        void hideProgressBlock();

        void hideKeyboard();

        void setLostSearch(String lostSearch);


    }

    interface Presenter extends BasePresenter<View> {
        void deleteTrack(DeezerTrack deezerTrack);

        void search(String serch);

        void nextSearch();

        void loadRepos(String title);
    }
}
