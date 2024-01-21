package com.example.facify;

public class UploadSongClass {
    public String name;
    public String url;

    public UploadSongClass(){

    }

    public UploadSongClass(String name,String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
