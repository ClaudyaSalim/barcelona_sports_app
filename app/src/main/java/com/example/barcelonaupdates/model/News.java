package com.example.barcelonaupdates.model;

import java.io.Serializable;

public class News implements Serializable {

    private int newsId;
    private int title;
    private int photo;
    private int newsDesc;

    public News(int newsId, int title, int photo, int newsDesc) {
        this.newsId = newsId;
        this.title = title;
        this.photo = photo;
        this.newsDesc = newsDesc;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(int newsDesc) {
        this.newsDesc = newsDesc;
    }
}
