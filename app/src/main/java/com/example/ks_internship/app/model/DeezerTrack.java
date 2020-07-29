package com.example.ks_internship.app.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class DeezerTrack {
    private String title;
    private String titleShord;
    private String link;
    private DeezerArtist artist;
    private DeezerAlbum album;

    public DeezerTrack(String title, String titleShord, String link, DeezerArtist artist, DeezerAlbum album) {

        this.titleShord = titleShord;
        this.link = link;
        this.artist = artist;
        this.album = album;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleShord() {
        return titleShord;
    }

    public void setTitleShord(String titleShord) {
        this.titleShord = titleShord;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DeezerArtist getArtist() {
        return artist;
    }

    public void setArtist(DeezerArtist artist) {
        this.artist = artist;
    }

    public DeezerAlbum getAlbum() {
        return album;
    }

    public void setAlbum(DeezerAlbum album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeezerTrack)) return false;
        DeezerTrack that = (DeezerTrack) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getTitleShord(), that.getTitleShord()) &&
                Objects.equals(getLink(), that.getLink()) &&
                Objects.equals(getArtist(), that.getArtist()) &&
                Objects.equals(getAlbum(), that.getAlbum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTitleShord(), getLink(), getArtist(), getAlbum());
    }

    @Override
    public String toString() {
        return "Title:" + title +
                "Artist:" + artist.getName() +
                "Album:" + album.getTitle();
    }
}
