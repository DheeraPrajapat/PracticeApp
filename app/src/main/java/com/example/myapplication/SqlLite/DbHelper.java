package com.example.myapplication.SqlLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="database_name";
    public static final String TABLE_NAME="table_name";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="create table "+TABLE_NAME+"(id INTEGER PRIMARY KEY, txt TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean addItem(String itemName)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("txt",itemName);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return true;
    }

    @SuppressLint("Range")
    public ArrayList getAllText()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ArrayList<String> arrayList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(" select * from "+TABLE_NAME,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("txt")));
            cursor.moveToNext();
        }
        return arrayList;
    }

}
