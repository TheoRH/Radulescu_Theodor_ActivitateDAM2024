package com.example.first21;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Huse")

public class HusaTelefon implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int KEy;
    private String material;
    private double lungime;
    private boolean avariat;

    public HusaTelefon(String material, double lungime, boolean avariat) {
        this.material = material;
        this.lungime = lungime;
        this.avariat = avariat;
    }

    protected HusaTelefon(Parcel in) {
        material = in.readString();
        lungime = in.readDouble();
        avariat = in.readByte() != 0;
    }

    public static final Creator<HusaTelefon> CREATOR = new Creator<HusaTelefon>() {
        @Override
        public HusaTelefon createFromParcel(Parcel in) {
            return new HusaTelefon(in);
        }

        @Override
        public HusaTelefon[] newArray(int size) {
            return new HusaTelefon[size];
        }
    };

    @Override
    public String toString() {
        return "HusaTelefon{" +
                "material='" + material + '\'' +
                ", lungime=" + lungime +
                ", avariat=" + avariat +
                '}';
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getLungime() {
        return lungime;
    }

    public void setLungime(double lungime) {
        this.lungime = lungime;
    }

    public boolean isAvariat() {
        return avariat;
    }

    public void setAvariat(boolean avariat) {
        this.avariat = avariat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(material);
        dest.writeDouble(lungime);
        dest.writeByte((byte) (avariat ? 1 : 0));
    }

    public int getKEy() {
        return KEy;
    }

    public void setKEy(int KEy) {
        this.KEy = KEy;
    }
}
