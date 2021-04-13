package com.unsplash.pickerandroid.example.dbase;

/*
 * April/2015, jgomez@ezmovil.net
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

    private com.unsplash.pickerandroid.example.dbase.DBHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    public static final String TABLE_LOGIN = "login";
    public static final String LOGIN_USER = "user";
    public static final String LOGIN_PASS = "password";

    public static final String CREATE_LOGIN = "create table " + TABLE_LOGIN + " ("
            + LOGIN_USER + " text,"
            + LOGIN_PASS + " text);";

    public void insert_login(String... params) {
        try {
            db.execSQL("insert into " + TABLE_LOGIN
                    + "("
                    + LOGIN_USER + "," + LOGIN_PASS + ")"
                    + " values ('" + params[0] + "','" + params[1] + "')"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // selects: January, 2020, jgomez@ezmovil.net

    public Cursor get_LoginbyUser(String user) {
        String buildSQL = "SELECT * FROM " + TABLE_LOGIN +
                " WHERE " + LOGIN_USER +" = '" + user + "'";
        try {
            return db.rawQuery(buildSQL, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Cursor get_Login() {
        //String buildSQL = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + LOGIN_USER + " = ''" + login + "'";
        String buildSQL = "SELECT * FROM " + TABLE_LOGIN ;
        try {
            return db.rawQuery(buildSQL, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int update_Login(String... str) {
        db.beginTransaction();
        try {
            ContentValues values=new ContentValues();
            values.put("user",str[0]);
            values.put("password",str[1]);
            int id=db.update(TABLE_LOGIN,values,"user='" + str + "'" ,null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction(); // commit or rollback
            return 1;
        } /*catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }*/
    }

    public void delete_login() {

        db.beginTransaction();
        try {
            db.delete(TABLE_LOGIN, null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction(); // commit or rollback
        } /*catch (Exception ex) {*/
    }

    //2021/Abril/HappyMondays

    public static final String TABLE_GALLERY = "gallery";
    public static final String GALLERY_ID = "id";
    public static final String GALLERY_LOGIN = "login";
    public static final String GALLERY_PASS = "password";
    public static final String GALLERY_IMAGE_BASE64 = "image";
    public static final String GALLERY_IMAGE_DIRECTORY = "directory";

    // creating tables
    public static final String CREATE_GALLERY = "create table " + TABLE_GALLERY + " ("
            + GALLERY_ID + " integer primary key autoincrement,"
            + GALLERY_LOGIN + " text,"
            + GALLERY_PASS + " text,"
            + GALLERY_IMAGE_BASE64 +  " text,"
            + GALLERY_IMAGE_DIRECTORY +  " text);";

    // insert GALLERY, update: ezmovil.net@gmail.com
    public void insert_gallery(String... params) {
        try {
            db.execSQL("insert into " + TABLE_GALLERY
                    + "("
                    + GALLERY_LOGIN + "," + GALLERY_PASS + "," + GALLERY_IMAGE_BASE64 + ","
                    + GALLERY_IMAGE_DIRECTORY +  ")"
                    + " values ('"
                    + params[0] + "','" + params[1] + "','" + params[2] + "','" + params[3] +  "')"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cursor getGallery() {
        String buildSQL = "SELECT * FROM " + TABLE_GALLERY ;
        try {
            return db.rawQuery(buildSQL, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void delete_imageDirectory(String position) {

        db.beginTransaction();
        try {
            db.delete(TABLE_GALLERY, " " +  "id = " + position, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction(); // commit or rollback
        }
    }


}

