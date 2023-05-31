package com.example.barcelonaupdates.model;

public class Games {

    private int gameId;
    private String gameName;
    private String date;
    private String time;
    private String category;
    private int price;

    public Games(int gameId, String gameName, String date, String time, String category, int price) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.date = date;
        this.time = time;
        this.category = category;
        this.price = price;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
