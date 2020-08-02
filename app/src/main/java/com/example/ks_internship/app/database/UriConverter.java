package com.example.ks_internship.app.database;

import android.net.Uri;

import androidx.room.TypeConverter;

public class UriConverter {
    @TypeConverter
    public String fromUri(Uri uri) {
        return uri.toString();
    }

    @TypeConverter
    public Uri toUri(String string) {
        return Uri.parse(string);
    }
}
