package com.example.postsapp;

public class ItemData {
    private String title;
    private String body;

    public ItemData(String title,String body){

        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
