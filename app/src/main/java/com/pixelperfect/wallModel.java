package com.pixelperfect;

public class wallModel {
   String url;
   int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public wallModel(String url, int id) {
        this.url = url;
        this.id =  id;
    }

    public  wallModel(){

    }
}
