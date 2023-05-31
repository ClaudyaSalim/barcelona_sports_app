package com.example.barcelonaupdates.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.barcelonaupdates.model.Games;

import java.util.ArrayList;

public class GamesHelper {

    Database dbHelper;
    Context context;
    SQLiteDatabase db;

    public GamesHelper(Context context) {
        this.context = context;
    }


    // akan dipanggil tiap mau akses database
    public void open(){
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // akan dipanggil ketika database tidak dipakai lagi
    public void close(){ dbHelper.close(); }

    public void insertGames(Games games){
        open();
        ContentValues values = new ContentValues();
        values.put("GameID", games.getGameId());
        values.put("GameName", games.getGameName());
        values.put("Date", games.getDate());
        values.put("Time", games.getTime());
        values.put("Category", games.getCategory());
        values.put("Price", games.getPrice());

        db.insert("Games", null, values);
        close();
    }

    public void listGames(){
        insertGames(new Games(1, "FC Barcelona vs Real Betis", "30 April 2023", "02.00", "Premiere League", 1700000));
        insertGames(new Games(2, "FC Barcelona vs CA Osasuna", "3 May 2023", "00.30", "Premiere League", 1500000));
        insertGames(new Games(3, "RCD Espanyol vs FC Barcelona", "15 May 2023", "02.00", "Premiere League", 3000000));
        insertGames(new Games(4, "FC Barcelona vs Real Sociedad", "21 May 2023", "23.00", "La Liga", 1500000));
        insertGames(new Games(5, "FC Barcelona vs RCD Mallorca", "28 May 2023", "23.00", "La Liga", 1500000));
    }

    public ArrayList<Games> getAllGames(){
        open();
        ArrayList<Games>gamesList = new ArrayList();
        String query = "SELECT * from Games";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                String category = cursor.getString(4);
                int price = cursor.getInt(5);
                Games game = new Games(id, name, date, time, category, price);
                gamesList.add(game);
            } while(cursor.moveToNext());
        }
        close();
        return gamesList;
    }

    public Games getGameByID(int id){
        open();

        String query = "SELECT * FROM Games WHERE GameID=?";
        String[]whereParam = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(query, whereParam);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        String name = cursor.getString(1);
        String date = cursor.getString(2);
        String time = cursor.getString(3);
        String category = cursor.getString(4);
        int price = cursor.getInt(5);
        Games game = new Games(id, name, date, time, category, price);

        close();
        return game;
    }

    public int getGameCount(){
        return getAllGames().size();
    }

}
