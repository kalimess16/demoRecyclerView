package com.example.demorecyclerview.RV;

import android.graphics.Bitmap;

public class Hero {
    private String mName;
   private Bitmap mImage;

    public Hero(String name, Bitmap image) {
        mName = name;
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        mImage = image;
    }
}
