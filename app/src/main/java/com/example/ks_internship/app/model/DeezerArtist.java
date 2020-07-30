package com.example.ks_internship.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class DeezerArtist implements Parcelable {
    private String name;
    private String link;

    public DeezerArtist(String name, String link) {

        this.name = name;
        this.link = link;
    }

    protected DeezerArtist(Parcel in) {
        name = in.readString();
        link = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
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
