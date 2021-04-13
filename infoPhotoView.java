package com.unsplash.pickerandroid.example.info;

public class infoPhotoView {

    private static infoPhotoView instance;

    public static infoPhotoView getInstance() {
        if (instance == null)
            instance = new infoPhotoView();
        return instance;
    }

    private infoPhotoView() {
    }

    private String val;

    public String getValue() {
        return val;
    }

    public void setValue(String value) {
        this.val = value;
    }
}