package com.pixelperfect;

public class catModel {

    String type;
    int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public catModel(String type, int img){

        this.img = img;
        this.type = type;
    }
}
