package com.example.iamma.simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    // use the variable to make database
    public static final String DATABASENAME = "SimpleSQLite.db";

    // use this variable to make table in database
    public static final String TABLENAME = "registeruser";
    // these are the attribute of the above table
    public static final String COL1 = "ID";
    public static final String COL2 = "USERNAME";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "PASSWORD";


    public DatabaseHelper(Context context) {
        super(context, DATABASENAME,null,1);

        SQLiteDatabase db = this.getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "  + TABLENAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME STRING ,EMAIL STRING, PASSWORD STRING)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }



    public boolean InsertData(String name, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        contentValues.put(COL4,password);

        long result = db.insert(TABLENAME ,null,contentValues);
        if(result ==-1)
            return  false;
        else
            return true;

    }
}
