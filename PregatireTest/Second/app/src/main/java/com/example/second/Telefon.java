package com.example.second;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.StringJoiner;

public class Telefon implements Parcelable {
    private String marca;
    private int dimensiune;
    private float baterie;
    private boolean avariat;

    public Telefon(String marca, int dimensiune, float baterie, boolean avariat) {
        this.marca = marca;
        this.dimensiune = dimensiune;
        this.baterie = baterie;
        this.avariat = avariat;
    }
    public Telefon() {
        this.marca = "n/a";
        this.dimensiune = 0;
        this.baterie = 0;
        this.avariat = false;
    }

    protected Telefon(Parcel in) {
        marca = in.readString();
        dimensiune = in.readInt();
        baterie = in.readFloat();
        avariat = in.readByte() != 0;
    }

    public static final Creator<Telefon> CREATOR = new Creator<Telefon>() {
        @Override
        public Telefon createFromParcel(Parcel in) {
            return new Telefon(in);
        }

        @Override
        public Telefon[] newArray(int size) {
            return new Telefon[size];
        }
    };

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(int dimensiune) {
        this.dimensiune = dimensiune;
    }

    public float getBaterie() {
        return baterie;
    }

    public void setBaterie(float baterie) {
        this.baterie = baterie;
    }

    public boolean isAvariat() {
        return avariat;
    }

    public void setAvariat(boolean avariat) {
        this.avariat = avariat;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Telefon.class.getSimpleName() + "[", "]")
                .add("marca='" + marca + "'")
                .add("dimensiune=" + dimensiune)
                .add("baterie=" + baterie)
                .add("avariat=" + avariat)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(dimensiune);
        dest.writeFloat(baterie);
        dest.writeByte((byte)(avariat?1:0));

    }
}
