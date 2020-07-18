package com.example.ks_internship.app.model;

import java.util.Objects;

public class Song {
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
                "Singer: " + singer  + "\n" +
                "Genre: " + genre  + "\n" +
                "Duration: " + alibom ;
    }
}
