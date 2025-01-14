package com.example.second2;

import android.os.Parcel;
import android.os.Parcelable;

public class Vesela implements Parcelable {
    private String denumire;
    private int numar;

    protected Vesela(Parcel in) {
        denumire = in.readString();
        numar = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeInt(numar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vesela> CREATOR = new Creator<Vesela>() {
        @Override
        public Vesela createFromParcel(Parcel in) {
            return new Vesela(in);
        }

        @Override
        public Vesela[] newArray(int size) {
            return new Vesela[size];
        }
    };

    @Override
    public String toString() {
        return "Vesela{" +
                "denumire='" + denumire + '\'' +
                ", numar=" + numar +
                '}';
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public Vesela(String denumire, int numar) {
        this.denumire = denumire;
        this.numar = numar;
    }
}
