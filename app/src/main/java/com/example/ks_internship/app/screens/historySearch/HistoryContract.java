package com.example.ks_internship.app.screens.historySearch;

import com.example.ks_internship.app.base.BasePresenter;
import com.example.ks_internship.app.base.BaseView;
import com.example.ks_internship.app.screens.choice.ChoiceContract;

import java.util.List;

public interface HistoryContract {
    interface View extends BaseView<Presenter> {
        void setArrayList(List<String> list);
    }

    interface Presenter extends BasePresenter<View> {


    }
}
