package com.example.myinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase loginDB) {
        loginDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "admin");
        contentValues.put("password", "admin");
        loginDB.insert("users", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase loginDB, int i, int i1) {
        loginDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = loginDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean validateUsername(String username) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean validateCredentials(String username, String password) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        System.out.println(String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
