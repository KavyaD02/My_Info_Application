package com.example.myinfo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBInfoHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Information.db";

    public DBInfoHelper(@Nullable Context context) {
        super(context, "Information.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase infoDB) {
        infoDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase infoDB, int i, int i1) {
        infoDB.execSQL("drop Table if exists users");
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

    public String obtainPassword(String username) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("Select * from users where username = ?", new String[]{username});
        @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex("password"));
        return pass;
    }
}
