package com.example.tugas_praktek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String DB_NAME = "Tugas_Praktek";

    public DatabaseHelper(@Nullable Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session (id INTEGER PRIMARY KEY,login TEXT)");
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, username, password TEXT)");
        db.execSQL("INSERT INTO session (id,login) VALUES (1,'unauthorized')");
        db.execSQL("CREATE TABLE books (id INTEGER PRIMARY KEY, title TEXT, publisher TEXT, page VARCHAR(10), year VARCHAR(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean checkSession(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{value});
        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean upgradeSession(String value,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login", value);
        long update = db.update("session", values,"id= " + id, null);
        if (update == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean saveData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long insert =db.insert("users", null, values);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = ? AND PASSWORD = ?", new String[]{username, password});
        if (c.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean InsertDataBook(String title, String publisher, String page, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("publisher", publisher);
        values.put("page", page);
        values.put("year", year);
        long result = db.insert("books", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor ReadAllData(){
        String query = "SELECT * FROM books";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void UpdateData(String title, String publisher, String page, String year, String id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("publisher", publisher);
        values.put("page", page);
        values.put("year", year);

        long result = db.update("books", values, "id=?", new String[]{id});
    }
}
