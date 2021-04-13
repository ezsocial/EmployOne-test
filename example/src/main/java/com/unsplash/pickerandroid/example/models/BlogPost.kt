package com.codingwithmitch.kotlinrecyclerviewexample.models

import android.graphics.Bitmap
import android.widget.ImageButton
import android.widget.ImageView

data class BlogPost(

    var title: String,

    var body: String,

    var image: String,

    var username: String ,

    var position: String ,

    var like: String ,

    var delete: String


) {

    override fun toString(): String {
        return "BlogPost(title='$title', image='$image', username='$username',position='$position', like='$like', delete='$delete)"
    }


}
























