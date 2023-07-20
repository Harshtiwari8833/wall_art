package com.pixelperfect;

public class wallModel {
   String url;
   String id;
   String cat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public wallModel(String url, String id, String cat) {
        this.url = url;
        this.id =  id;
        this.cat =cat;
    }

    public  wallModel(){

    }
}
