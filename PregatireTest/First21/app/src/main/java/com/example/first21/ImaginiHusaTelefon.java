package com.example.first21;

import android.graphics.Bitmap;

public class ImaginiHusaTelefon {
    private String url;
    private String text;
    private Bitmap iamgine;

    public ImaginiHusaTelefon(String url, String text, Bitmap iamgine) {
        this.url = url;
        this.text = text;
        this.iamgine = iamgine;
    }

    @Override
    public String toString() {
        return "ImaginiHusaTelefon{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", iamgine=" + iamgine +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getIamgine() {
        return iamgine;
    }

    public void setIamgine(Bitmap iamgine) {
        this.iamgine = iamgine;
    }
}
