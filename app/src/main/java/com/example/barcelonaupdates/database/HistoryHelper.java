package com.example.barcelonaupdates.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.barcelonaupdates.model.History;

import java.util.ArrayList;

public class HistoryHelper {

    Database dbHelper;
    Context context;
    SQLiteDatabase db;

    public HistoryHelper(Context context) {
        this.context = context;
    }

    // akan dipanggil tiap mau akses database
    public void open(){
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // akan dipanggil ketika database tidak dipakai lagi
    public void close(){ dbHelper.close(); }

    public void insertHistory(History history){
        open();
        ContentValues values = new ContentValues();
        values.put("HistoryID", history.getHistoryId());
        values.put("UserID", history.getUserId());
        values.put("GameID", history.getGameId());
        values.put("Qty", history.getQty());
        values.put("TotalPrice", history.getTotalPrice());

        db.insert("History", null, values);
        close();
    }

    // untuk keperluan debugging saja
    public ArrayList<History>getAllHistories(){
        open();
        ArrayList<History>historyList = new ArrayList<>();
        String query = "SELECT * FROM History";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int historyId = cursor.getInt(0);
                int userId = cursor.getInt(1);
                int gameId = cursor.getInt(2);
                int qty = cursor.getInt(3);
                int totalPrice = cursor.getInt(4);

                History history = new History(historyId, userId, gameId, qty, totalPrice);
                historyList.add(history);
            } while (cursor.moveToNext());
        }

        close();
        return historyList;
    }

    public ArrayList<History>getAllHistoriesbyUser(int userId){
        open();
        ArrayList<History>historyList = new ArrayList<>();
        String query = "SELECT * FROM History WHERE UserID=?";
        String[]whereParam = {String.valueOf(userId)};
        Cursor cursor = db.rawQuery(query, whereParam);

        if(cursor.moveToFirst()){
            do{
                int historyId = cursor.getInt(0);
                int gameId = cursor.getInt(2);
                int qty = cursor.getInt(3);
                int totalPrice = cursor.getInt(4);

                History history = new History(historyId, userId, gameId, qty, totalPrice);
                historyList.add(history);
            } while (cursor.moveToNext());
        }

        close();
        return historyList;
    }
}
