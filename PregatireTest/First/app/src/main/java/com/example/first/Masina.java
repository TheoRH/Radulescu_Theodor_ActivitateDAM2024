package com.example.first;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Masina implements Parcelable {
    private int km;
    private String marca;
    private float consum;
    private boolean avariat;
    private String detalii;

    public Masina(int km, String marca, float consum, boolean avariat, String detalii) {
        this.km = km;
        this.marca = marca;
        this.consum = consum;
        this.avariat = avariat;
        this.detalii = detalii;
    }
    public Masina() {
        this.km = 0;
        this.marca = "Necunoscut";
        this.consum = 0;
        this.avariat = false;
        this.detalii = "n/a";
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getConsum() {
        return consum;
    }

    public void setConsum(float consum) {
        this.consum = consum;
    }

    public boolean isAvariat() {
        return avariat;
    }

    public void setAvariat(boolean avariat) {
        this.avariat = avariat;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Masina{");
        sb.append("km=").append(km);
        sb.append(", marca='").append(marca).append('\'');
        sb.append(", consum=").append(consum);
        sb.append(", avariat=").append(avariat);
        sb.append(", detalii='").append(detalii).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(km);
        dest.writeString(marca);
        dest.writeFloat(consum);
        dest.writeByte((byte) (avariat ? 1:0));
        dest.writeString(detalii);

    }

    protected Masina(Parcel in)
    {
        km=in.readInt();
        marca=in.readString();
        consum=in.readFloat();
        avariat=in.readByte()!=0;
        detalii=in.readString();
    }

    public static final Creator<Masina> CREATOR = new Creator<Masina>() {
        @Override
        public Masina createFromParcel(Parcel source) {
            return new Masina(source);
        }

        @Override
        public Masina[] newArray(int size) {
            return new Masina[size];
        }
    };
}
