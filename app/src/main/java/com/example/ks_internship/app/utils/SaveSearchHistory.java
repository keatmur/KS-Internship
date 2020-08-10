package com.example.ks_internship.app.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.ks_internship.app.activity.MainActivity;

import java.util.ArrayList;
import java.util.Set;

public class SaveSearchHistory {
    private Context context;

    public SaveSearchHistory(Context context) {
        this.context=context;
    }

    private SharedPreferences getPrefs(){
        return context.getSharedPreferences(Constants.PREFD_NAME,Context.MODE_PRIVATE);
    }
    public  void setInputSearch( String strings) {
        getPrefs().edit().putString(Constants.PRFED_INPUT_SEARCH,  strings).apply(); //TODO: or commit()?
    }
    public  String getInputSearch() {
        return getPrefs().getString(Constants.PRFED_INPUT_SEARCH,"");
    }



    public  void setTitleSearch( String strings) {
        getPrefs().edit().putString(Constants.PRFED_TITLES,  strings).apply(); //TODO: or commit()?
    }

    public  String getTitles() {
        return getPrefs().getString(Constants.PRFED_TITLES,"");
    }


}
