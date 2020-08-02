package com.example.ks_internship.app.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class DeezerArtist implements Parcelable {
    @ColumnInfo(name = "idArtist")
    private int  id;
    @ColumnInfo(name = "nameArtist")
    private String name;

    public DeezerArtist(int id,String name) {
        this.id=id;
        this.name = name;

    }

    protected DeezerArtist(Parcel in) {
        id=in.readInt();
        name = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeezerArtist> CREATOR = new Creator<DeezerArtist>() {
        @Override
        public DeezerArtist createFromParcel(Parcel in) {
            return new DeezerArtist(in);
        }

        @Override
        public DeezerArtist[] newArray(int size) {
            return new DeezerArtist[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeezerArtist)) return false;
        DeezerArtist that = (DeezerArtist) o;
        return Objects.equals(getName(), that.getName()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
