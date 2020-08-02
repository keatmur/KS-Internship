package com.example.ks_internship.app.database;


import com.example.ks_internship.app.model.DeezerTrack;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DeezerTrack.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepoTrackDao getPersonDao();
}
