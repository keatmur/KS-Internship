package com.example.ks_internship.app.model;

import java.util.Objects;

public class DeezerArtist {
    private String name;
    private String link;

    public DeezerArtist(String name,String link) {

        this.name = name;
        this.link =link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeezerArtist)) return false;
        DeezerArtist that = (DeezerArtist) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getLink(), that.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLink());
    }
}
