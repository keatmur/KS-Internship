package com.example.ks_internship.app.model;

import java.util.Objects;

public class DeezerAlbum {
    private String title;

    public DeezerAlbum(String title) {
        this.title = title;
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
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
