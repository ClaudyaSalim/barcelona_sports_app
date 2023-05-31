package com.example.barcelonaupdates.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.barcelonaupdates.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {
    Database dbHelper;
    Context context;
    SQLiteDatabase db;

    public UserHelper(Context context) {
        this.context = context;
    }


    // akan dipanggil tiap mau akses database
    public void open(){
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // akan dipanggil ketika database tidak dipakai lagi
    public void close(){ dbHelper.close(); }

    public ArrayList<User> getAllUsers(){
        open();
        ArrayList<User> userList = new ArrayList();
        String query = "SELECT * from User";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String pass = cursor.getString(3);
                User user = new User(id, name, email, pass);
                userList.add(user);
            } while(cursor.moveToNext());
        }
        close();
        return userList;
    }

//    public void getUserByLogin(String email, String pass){
//
//    }

    public User getUserByID(int id){
        open();

        String query = "SELECT * FROM User WHERE UserID=?";
        String[]whereParam = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(query, whereParam);
        if(cursor!=null){
            cursor.moveToFirst();
        }

        String name = cursor.getString(1);
        String email = cursor.getString(2);
        String pass = cursor.getString(3);
        User user = new User(id, name, email, pass);

        close();
        return user;
    }

    public void regisUser(User user){
        open();
        ContentValues values = new ContentValues();
        values.put("UserID", user.getUserId());
        values.put("UserName", user.getUserName());
        values.put("Email", user.getEmail());
        values.put("Password", user.getPassword());

        db.insert("User", null, values);
        close();
    }

}
