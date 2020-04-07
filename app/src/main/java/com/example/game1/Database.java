package com.example.game1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Coins.db";
    public static final String TABLE_NAME ="coinhint";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="COINS";
    public static final String COL_3 ="HINTS";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME,  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,COINS INTEGER,HINT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS 'Scoinhint'");
        onCreate(sqLiteDatabase);
    }
    public boolean insertdata(String coins, String hints) {
        SQLiteDatabase sqLiteOpenHelper = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        if(coins==null) {
            contentValues.put(COL_2,1500);
            contentValues.put(COL_3,5);
        }
        long result = sqLiteOpenHelper.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getData(){
        SQLiteDatabase sqLiteOpenHelper=this.getWritableDatabase();
        Cursor res =sqLiteOpenHelper.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
}
