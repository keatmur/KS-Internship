package com.example.ks_internship.app.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Song  implements Parcelable {
    private String name;
    private String singer;
    private String genre;
    private String alibom;

    public Song(String name, String singer, String genre, String alibom) {
        this.name = name;
        this.singer = singer;
        this.genre = genre;
        this.alibom = alibom;
    }

    protected Song(Parcel in) {
        name = in.readString();
        singer = in.readString();
        genre = in.readString();
        alibom = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getAlibom() {
        return alibom;
    }

    public void setAlibom(String alibom) {
        this.alibom = alibom;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(name, song.name) &&
                Objects.equals(singer, song.singer) &&
                Objects.equals(genre, song.genre) &&
                Objects.equals(alibom, song.alibom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, singer, genre, alibom);
    }


    @Override
    public String toString() {
        return
                "Name: " + name + "\n" +
                        "Singer: " + singer + "\n" +
                        "Genre: " + genre + "\n" +
                        "Duration: " + alibom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(singer);
        parcel.writeString(genre);
        parcel.writeString(alibom);
    }
}
