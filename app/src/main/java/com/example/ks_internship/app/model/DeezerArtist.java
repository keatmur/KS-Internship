package com.example.ks_internship.app.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class DeezerArtist implements Parcelable {
    private String name;
    private Uri link;

    public DeezerArtist(String name, Uri link) {

        this.name = name;
        this.link = link;
    }

    protected DeezerArtist(Parcel in) {
        name = in.readString();
        link = Uri.parse(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link.toString());
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

    public Uri getLink() {
        return link;
    }

    public void setLink(Uri link) {
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
