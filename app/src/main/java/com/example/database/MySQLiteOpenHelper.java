package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String ID = "id";
    private static final String DATABASE_NAME = "mydata.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME = "memodata";
    private static final String TITLE = "title";
    private static final String MEMO = "memo";

    MySQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "create table " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY," +
                TITLE + " TEXT," +
                MEMO + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
