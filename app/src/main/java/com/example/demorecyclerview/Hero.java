package com.example.demorecyclerview;

public class Hero {
    private String mName;
   private int mImage;

    public Hero(String name, int image) {
        mName = name;
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }
}
