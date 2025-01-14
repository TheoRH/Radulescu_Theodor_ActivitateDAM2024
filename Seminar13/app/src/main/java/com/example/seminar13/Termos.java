package com.example.seminar13;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Termosuri")
public class Termos implements Parcelable {


    private String nume; //rez
    @PrimaryKey(autoGenerate = true)
    private int numar;//rez
    private String detalii;//rez
    private boolean curat;//rez
    private float grade;//rez

    public Termos(String nume, int numar, String detalii, boolean curat, float grade) {
        this.nume = nume;
        this.numar = numar;
        this.detalii = detalii;
        this.curat = curat;
        this.grade = grade;
    }

    protected Termos(Parcel in) {
        nume = in.readString();
        numar = in.readInt();
        detalii = in.readString();
        curat = in.readByte() != 0;
        grade = in.readFloat();
    }

    public static final Creator<Termos> CREATOR = new Creator<Termos>() {
        @Override
        public Termos createFromParcel(Parcel in) {
            return new Termos(in);
        }

        @Override
        public Termos[] newArray(int size) {
            return new Termos[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public boolean isCurat() {
        return curat;
    }

    public void setCurat(boolean curat) {
        this.curat = curat;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
    public String getKey(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.nume);
        sb.append(this.numar);
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Termos{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", numar=").append(numar);
        sb.append(", detalii='").append(detalii).append('\'');
        sb.append(", curat=").append(curat);
        sb.append(", grade=").append(grade);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeInt(numar);
        dest.writeString(detalii);
        dest.writeByte((byte)(curat?1:0));
        dest.writeFloat(grade);


    }
}
