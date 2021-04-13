package com.unsplash.pickerandroid.example

import android.app.Application
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // initializing the picker library
        UnsplashPhotoPicker.init(
            this,
            "WoZX_ei8bEyVWQvfapkOtvyf3bSdmj6HKT2xA8IImzw",
            "pinqAO08FQ0QSBJzZhLEGp9LVf_FA8MkUlHw05Y-0jY"
            /* optional page size (number of photos per page) */
        )
            /* .setLoggingEnabled(true) // if you want to see the http requests */
    }
}
