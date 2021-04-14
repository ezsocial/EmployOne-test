package com.unsplash.pickerandroid.example

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.unsplash.pickerandroid.example.dbase.DataBaseManager
import com.unsplash.pickerandroid.example.info.infoContext
import com.unsplash.pickerandroid.example.info.infoLogin
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_unsplash_main.*


class MainUnsplashActivity : AppCompatActivity() {

    val str = "UnsplashPhoto(id=CvSKBdkMoyE, created_at=2021-04-12T08:29:36-04:00, width=4000, height=6000, color=#f3f3f3, likes=62, description=null, urls=UnsplashUrls(thumb=https://images.unsplash.com/photo-1618230302855-d74d0b4dcd34?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MnwzMTcwOTl8fHx8fDJ8fDE2MTgyNzQwNzQ&ixlib=rb-1.2.1&q=80&w=200, small=https://images.unsplash.com/photo-1618230302855-d74d0b4dcd34?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MnwzMTcwOTl8fHx8fDJ8fDE2MTgyNzQwNzQ&ixlib=rb-1.2.1&q=80&w=400, medium=null, regular=https://images.unsplash.com/photo-1618230302855-d74d0b4dcd34?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MnwzMTcwOTl8fHx8fDJ8fDE2MTgyNzQwNzQ&ixlib=rb-1.2.1&q=80&w=1080, large=null, full=https://images.unsplash.com/photo-1618230302855-d74d0b4dcd34?crop=entropy&cs=srgb&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MnwzMTcwOTl8fHx8fDJ8fDE2MTgyNzQwNzQ&ixlib=rb-1.2.1&q=85, raw=https://images.unsplash.com/photo-1618230302855-d74d0b4dcd34?ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MnwzMTcwOTl8fHx8fDJ8fDE2MTgyNzQwNzQ&ixlib=rb-1.2.1), links=UnsplashLinks(self=https://api.unsplash.com/photos/CvSKBdkMoyE, html=https://unsplash.com/photos/CvSKBdkMoyE, photos=null, likes=null, portfolio=null, download=https://unsplash.com/photos/CvSKBdkMoyE/download, download_location=https://api.unsplash.com/photos/CvSKBdkMoyE/download?ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MnwzMTcwOTl8fHx8fDJ8fDE2MTgyNzQwNzQ), user=UnsplashUser(id=3-NSJcjjIEA, username=joshuafuller, name=Joshua Fuller, portfolio_url=http://www.JFullerLife.com, bio=UK Photographer / Landscapes / Lifestyle / Automotive , location=London UK, total_likes=408, total_photos=459, total_collection=0, profile_image=UnsplashUrls(thumb=null, small=https://images.unsplash.com/profile-1554115735018-2a9dad8cd52d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32, medium=https://images.unsplash.com/profile-1554115735018-2a9dad8cd52d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64, regular=null, large=https://images.unsplash.com/profile-1554115735018-2a9dad8cd52d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128, full=null, raw=null), links=UnsplashLinks(self=https://api.unsplash.com/users/joshuafuller, html=https://unsplash.com/@joshuafuller, photos=https://api.unsplash.com/users/joshuafuller/photos, likes=https://api.unsplash.com/users/joshuafuller/likes, portfolio=https://api.unsplash.com/users/joshuafuller/portfolio, download=null, download_location=null)))"

    private var manager: DataBaseManager? = null;
    private var mContext: Context? = null;


    private lateinit var mAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unsplash_main)

        //applicationContext.getSt
        //val photos: ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)


        val inflater = this.layoutInflater
        val v_iew = inflater.inflate(R.layout.login, null);

        mContext = this@MainUnsplashActivity
        var _infoContext = infoContext()
        _infoContext._context = mContext

        val txtEmail = v_iew.findViewById<View>(R.id.emailText) as EditText
        val txtPass = v_iew.findViewById<View>(R.id.passText) as EditText
        // result adapter
        // recycler view configuration
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.itemAnimator = null
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = PhotoAdapter(this)
        main_recycler_view.adapter = mAdapter
        // on the pick button click, we start the library picker activity
        // we are expecting a result from it so we start it for result
        main_pick_button.setOnClickListener {
            startActivityForResult(
                    UnsplashPickerActivity.getStartingIntent(
                            this,
                            !main_single_radio_button.isChecked
                    ), REQUEST_CODE
            )
        }

        prepareLogin()

        val _infoLogin = infoLogin()

        if (_infoLogin.loginPass != null) {
            if (!_infoLogin.loginPass.isEmpty()) {
                txtEmail.setText(_infoLogin.loginUser)
                txtPass.setText(_infoLogin.loginPass)
            }
        }

        manager?.insert_login("ezmovil.net@gmail.com", "1234");

        /*val dialog = AlertDialog.Builder(this)
                .setTitle("Sign user")
                .setMessage("user")
                .setView(v_iew)
                .setNegativeButton("Save") { view, _ ->
                    Toast.makeText(this, "Updating user" + txtEmail.text.toString()
                            , Toast.LENGTH_SHORT).show()
                    manager?.insert_login(txtEmail.text.toString(), txtPass.text.toString());
                    view.dismiss()
                }
                .setPositiveButton("Cancel") { view, _ ->
                    Toast.makeText(this, "Ok button pressed", Toast.LENGTH_SHORT).show()
                    view.dismiss()
                }
                .setCancelable(false)
                .create()

        dialog.show()*/
        
        findViewById<FloatingActionButton>(R.id.fabUnsplash).setOnClickListener { view ->
            Snackbar.make(view, "Unsplash DEMO", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val intent = Intent(this@MainUnsplashActivity, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this@MainUnsplashActivity.startActivity(intent)

        }
    }

    // here we are receiving the result from the picker activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            // getting the photos
            val photos: ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            // showing the preview
            mAdapter.setListOfPhotos(photos)
            // telling the user how many have been selected
            Toast.makeText(this, "number of selected photos: " + photos?.size, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        // dummy request code to identify the request
        private const val REQUEST_CODE = 123
    }


    private fun prepareLogin() {
        mContext = this@MainUnsplashActivity
        manager = DataBaseManager(mContext)
        val _infoLogin = infoLogin()
        //this.manager!!.delete_login()
        val _login: Cursor
        _login = this.manager!!.get_Login()
        val _count = _login.count
        if (_login != null && _count > 0) {
            if (_login.moveToFirst()) {
                do {
                    _infoLogin.loginUser = _login.getString(_login.getColumnIndex("user"));
                    _infoLogin.loginPass = _login.getString(_login.getColumnIndex("password"));
                } while (_login.moveToNext())
            }
        }
    }

}
