package com.example.third;

import android.os.Parcel;
import android.os.Parcelable;

public class Facultate implements Parcelable {
    private String name;
    private int studenti;

    public Facultate(String name, int studenti) {
        this.name = name;
        this.studenti = studenti;
    }
    public Facultate() {
        this.name = "n/a";
        this.studenti = 0;
    }

    protected Facultate(Parcel in) {
        name = in.readString();
        studenti = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(studenti);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Facultate> CREATOR = new Creator<Facultate>() {
        @Override
        public Facultate createFromParcel(Parcel in) {
            return new Facultate(in);
        }

        @Override
        public Facultate[] newArray(int size) {
            return new Facultate[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudenti() {
        return studenti;
    }

    public void setStudenti(int studenti) {
        this.studenti = studenti;
    }
}
