package com.example.barcelonaupdates.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.barcelonaupdates.R;
import com.example.barcelonaupdates.model.Games;
import com.example.barcelonaupdates.model.News;

public class Database extends SQLiteOpenHelper {


    public Database(Context context) {
        super(context, "Barcelona", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUser = "CREATE TABLE User" + "(UserID INTEGER PRIMARY KEY, UserName, Email, Password)";
//        String queryNews = "CREATE TABLE News" + "(NewsID INTEGER PRIMARY KEY, Title INTEGER, Photo INTEGER, NewsDesc INTEGER)";
        String queryGames = "CREATE TABLE Games" + "(GameID INTEGER PRIMARY KEY,  GameName, Date, Time, Category, Price INTEGER)";
        String queryPurchase = "CREATE TABLE History" + "(HistoryID INTEGER PRIMARY KEY,  UserID INTEGER, GameID INTEGER, Qty INTEGER, TotalPrice INTEGER)";

        db.execSQL(queryUser);
//        db.execSQL(queryNews);
        db.execSQL(queryGames);
        db.execSQL(queryPurchase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryUser = "DROP TABLE User";
//        String queryNews = "DROP TABLE News";
        String queryGames = "DROP TABLE Games";
        String queryPurchase = "DROP TABLE Purchase";
        db.execSQL(queryUser);
//        db.execSQL(queryNews);
        db.execSQL(queryGames);
        db.execSQL(queryPurchase);
        onCreate(db);
    }

}
