package com.example.seminar13;

public class AccuAPI {
    private String key;

    public AccuAPI(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "AccuAPI{" +
                "key='" + key + '\'' +
                '}';
    }
}
