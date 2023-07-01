package com.pixelperfect;

public class wallModel {
    String img;
    String key;

    public wallModel(String img,String key)
    {
        this.img = img;
        this.key=key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public  wallModel( ){}
}
