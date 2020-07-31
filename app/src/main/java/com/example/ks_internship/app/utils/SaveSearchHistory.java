package com.example.ks_internship.app.utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.ArrayList;
import java.util.Set;

public class SaveSearchHistory {

    private static  SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(Constants.PREFD_NAME,Context.MODE_PRIVATE);
    }
    public static void setTitleSearch(Context context, String strings) {
        getPrefs(context).edit().putString(Constants.PRFED_TITLES,  strings).apply(); //TODO: or commit()?
    }

    public static String getTitles(Context context) {
        return getPrefs(context).getString(Constants.PRFED_TITLES,"");
    }


}
