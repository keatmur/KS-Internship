package com.example.ks_internship.app.database;

import com.example.ks_internship.app.model.DeezerTrack;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RepoTrackDao {
    @Insert
    void insertTrack(DeezerTrack deezerTrack);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllTracks(List<DeezerTrack> trackList);

    @Delete
    void deleteTrack(DeezerTrack deezerTrack);

    @Query("DELETE FROM track")
    void deleteAllTracks();

    @Query("SELECT* FROM track")
    LiveData<List<DeezerTrack>> getAllTrackS();

    @Query("SELECT* FROM track where id=:idGet")
    LiveData<DeezerTrack> getTrack(int idGet);

}
