package com.example.ks_internship.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


public class DeezerAlbum implements Parcelable {
    @ColumnInfo(name = "albumId")
    private int id;
    @ColumnInfo(name = "titleAlbum")
    private String title;

    @ColumnInfo(name = "urlCoverSmall")
    @SerializedName("cover_small")
    private String coverSmall;

    @ColumnInfo(name = "urlCoverMedium")
    @SerializedName("cover_medium")
    private String coverMedium;


    public DeezerAlbum(int id, String title, String coverSmall, String coverMedium) {
        this.id = id;
        this.title = title;
        this.coverSmall = coverSmall;
        this.coverMedium = coverMedium;
    }

    protected DeezerAlbum(Parcel in) {
        id=in.readInt();
        title = in.readString();
        coverSmall = in.readString();
        coverMedium = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(coverSmall);
        dest.writeString(coverMedium);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeezerAlbum> CREATOR = new Creator<DeezerAlbum>() {
        @Override
        public DeezerAlbum createFromParcel(Parcel in) {
            return new DeezerAlbum(in);
        }

        @Override
        public DeezerAlbum[] newArray(int size) {
            return new DeezerAlbum[size];
        }
    };

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

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeezerAlbum)) return false;
        DeezerAlbum that = (DeezerAlbum) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getCoverSmall(), that.getCoverSmall()) &&
                Objects.equals(getCoverMedium(), that.getCoverMedium());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCoverSmall(), getCoverMedium());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
