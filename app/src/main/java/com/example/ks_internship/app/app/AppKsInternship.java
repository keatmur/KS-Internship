package com.example.ks_internship.app.app;

import android.app.Application;

import com.example.ks_internship.app.database.AppDatabase;

import androidx.room.Room;

public class AppKsInternship extends Application {

    public static AppKsInternship instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppKsInternship getInstance() {
        return instance;
    }



    public AppDatabase getDatabase() {
        return database;
    }


}
