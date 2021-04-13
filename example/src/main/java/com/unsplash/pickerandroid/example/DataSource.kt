package com.codingwithmitch.kotlinrecyclerviewexample

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.codingwithmitch.kotlinrecyclerviewexample.models.BlogPost
import com.unsplash.pickerandroid.example.R
import com.unsplash.pickerandroid.example.dbase.DataBaseManager
import com.unsplash.pickerandroid.example.info.infoContext


class DataSource{

    companion object{


        fun createDataSet(): ArrayList<BlogPost>{

            val list = ArrayList<BlogPost>()

            var manager: DataBaseManager? = null;
            val _infoContext = infoContext()
            var mContext = _infoContext._context
            manager = DataBaseManager(mContext)

            //this.manager!!.delete_login()
            val _images: Cursor
            _images = manager!!.gallery
            val _count = _images.count
            if (_images != null && _count > 0) {
                if (_images.moveToFirst()) {
                    do {

                        //_infoLogin.loginUser = _images.getString(_images.getColumnIndex("user"));
                        //_infoLogin.loginPass = _login.getString(_login.getColumnIndex("password"));
                        var lastimg: String = "/data/user/0/com.unsplash.pickerandroid.example/app_Images/" +
                                _images.getString(_images.getColumnIndex("directory"))

                        var position: String = _images.getString(_images.getColumnIndex("id"))


                        if (_images.getString(_images.getColumnIndex("directory")) != "null") {

                            list.add(
                                BlogPost(
                                    "Juan Gomez +502 5489 4239",
                                    "Happy Mondays",
                                    lastimg,
                                    "ezmovil.net@gmail.com",
                                    position,
                                    "like",
                                    "delete"
                                )
                            )
                        }
                    } while (_images.moveToNext())
                }
            }
            /*
            list.add(
                BlogPost(
                    "Senior Android Engineer - Kaushik Gopal",
                    "Kaushik Gopal is a Senior Android Engineer working in Silicon Valley.\r\n\r\nHe works as a Senior Staff engineer at Instacart.\r\n\r\nInstacart: https://www.instacart.com/",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/senior_android_engineer_kaushik_gopal.png",
                    "mitch"
                )
            )*/
            return list
        }

        fun String.toBitmap():Bitmap?{
            Base64.decode(this,Base64.DEFAULT).apply {
                return BitmapFactory.decodeByteArray(this,0,size)
            }
        }
    }
}