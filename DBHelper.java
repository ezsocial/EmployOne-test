package com.unsplash.pickerandroid.example.dbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*
* April/2015, jgomez@ezmovil.net
*/

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "happymondays.db2";
    private static final int DB_SCHEME_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager.CREATE_GALLERY);
        db.execSQL(DataBaseManager.CREATE_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS gallery");
        db.execSQL("DROP TABLE IF EXISTS login");
        onCreate(db);
    }
}

