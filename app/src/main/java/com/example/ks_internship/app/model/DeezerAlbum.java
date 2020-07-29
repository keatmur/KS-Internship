package com.example.ks_internship.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DeezerAlbum {
    private String title;

    @SerializedName("cover_small")
    private String coverSmall;

    public DeezerAlbum(String title, String coverSmall) {
        this.title = title;
        this.coverSmall = coverSmall;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeezerAlbum)) return false;
        DeezerAlbum that = (DeezerAlbum) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getCoverSmall(), that.getCoverSmall());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCoverSmall());
    }



}
