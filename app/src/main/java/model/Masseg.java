package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Masseg implements Parcelable {
private String text;

    public Masseg(String text) {
        this.text = text;
    }


    protected Masseg(Parcel in) {
        text = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Masseg> CREATOR = new Creator<Masseg>() {
        @Override
        public Masseg createFromParcel(Parcel in) {
            return new Masseg(in);
        }

        @Override
        public Masseg[] newArray(int size) {
            return new Masseg[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Masseg)) return false;
        Masseg masseg = (Masseg) o;
        return Objects.equals(getText(), masseg.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText());
    }
}
