package com.example.ks_internship.app.model;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.ks_internship.app.database.UriConverter;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "track")
@TypeConverters({UriConverter.class})
public class DeezerTrack implements Parcelable {
    @PrimaryKey
    private int id;
    private String title;
    @SerializedName("title_short")
    private String titleShord;
    private Uri link;
    @Embedded
    private DeezerArtist artist;
    @Embedded
    private DeezerAlbum album;

    public DeezerTrack(int id,String title, String titleShord, Uri link, DeezerArtist artist, DeezerAlbum album) {
        this.id=id;
        this.title=title;
        this.titleShord = titleShord;
        this.link = link;
        this.artist = artist;
        this.album = album;

    }


    protected DeezerTrack(Parcel in) {
        id=in.readInt();
        title = in.readString();
        titleShord = in.readString();
        link = Uri.parse(in.readString());
        artist = in.readParcelable(DeezerArtist.class.getClassLoader());
        album = in.readParcelable(DeezerAlbum.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(titleShord);
        dest.writeString(link.toString());
        dest.writeParcelable(artist, flags);
        dest.writeParcelable(album, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeezerTrack> CREATOR = new Creator<DeezerTrack>() {
        @Override
        public DeezerTrack createFromParcel(Parcel in) {
            return new DeezerTrack(in);
        }

        @Override
        public DeezerTrack[] newArray(int size) {
            return new DeezerTrack[size];
        }
    };

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

    public Uri getLink() {
        return link;
    }

    public void setLink(Uri link) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Title:" + title + "\n" +
                "Artist:" + artist.getName() + "\n" +
                "Album:" + album.getTitle() + "\n" +
                "Link in Deezer: " + link;
    }

}
