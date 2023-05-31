package com.example.barcelonaupdates.model;

public class History {

    private int historyId;
    private int userId;
    private int gameId;
    private int qty;
    private int totalPrice;

    public History(int historyId, int userId, int gameId, int qty, int totalPrice) {
        this.historyId = historyId;
        this.userId = userId;
        this.gameId = gameId;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
