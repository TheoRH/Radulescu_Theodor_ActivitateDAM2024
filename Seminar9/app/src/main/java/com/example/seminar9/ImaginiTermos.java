package com.example.seminar9;

import android.graphics.Bitmap;

public class ImaginiTermos {
    private String textAfisat;
    private Bitmap imagine;
    private String link;


    public ImaginiTermos(String textAfisat, Bitmap imagine, String link) {
        this.textAfisat = textAfisat;
        this.imagine = imagine;
        this.link = link;
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public void setTextAfisat(String textAfisat) {
        this.textAfisat = textAfisat;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImaginiTermos{" +
                "textAfisat='" + textAfisat + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
