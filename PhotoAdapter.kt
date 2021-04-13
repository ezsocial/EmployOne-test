package com.unsplash.pickerandroid.example

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import com.unsplash.pickerandroid.example.dbase.DataBaseManager
import com.unsplash.pickerandroid.example.info.infoContext
import com.unsplash.pickerandroid.example.info.infoLogin
import com.unsplash.pickerandroid.example.info.infoPhotoView
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import kotlinx.android.synthetic.main.item_photo.view.*
import java.io.*
import java.lang.Exception
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

class PhotoAdapter constructor(context: Context) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {


    private var manager: DataBaseManager? = null;
    private var mContext: Context? = null;

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var mListOfPhotos: List<UnsplashPhoto> = mutableListOf()

    //"UnsplashPhoto(id=ORgQwgGMSmQ, created_at=2021-04-11T18:05:34-04:00, width=4000, height=6000, color=#0c2626, likes=74, description=null, urls=UnsplashUrls(thumb=https://images.unsplash.com/photo-1618178498628-a4e5519b77c5?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8fDE2MTgyNTk2MzY&ixlib=rb-1.2.1&q=80&w=200, small=https://images.unsplash.com/photo-1618178498628-a4e5519b77c5?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8fDE2MTgyNTk2MzY&ixlib=rb-1.2.1&q=80&w=400, medium=null, regular=https://images.unsplash.com/photo-1618178498628-a4e5519b77c5?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8fDE2MTgyNTk2MzY&ixlib=rb-1.2.1&q=80&w=1080, large=null, full=https://images.unsplash.com/photo-1618178498628-a4e5519b77c5?crop=entropy&cs=srgb&fm=jpg&ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8fDE2MTgyNTk2MzY&ixlib=rb-1.2.1&q=85, raw=https://images.unsplash.com/photo-1618178498628-a4e5519b77c5?ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8fDE2MTgyNTk2MzY&ixlib=rb-1.2.1), links=UnsplashLinks(self=https://api.unsplash.com/photos/ORgQwgGMSmQ, html=https://unsplash.com/photos/ORgQwgGMSmQ, photos=null, likes=null, portfolio=null, download=https://unsplash.com/photos/ORgQwgGMSmQ/download, download_location=https://api.unsplash.com/photos/ORgQwgGMSmQ/download?ixid=MnwyMjEzMzN8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8fDE2MTgyNTk2MzY), user=UnsplashUser(id=dyJ3JpaC5wQ, username=martberrios, name=Martín Berrios, portfolio_url=null, bio=I make a videos and sometimes photos, location=Chile, total_likes=0, total_photos=29, total_collection=0, profile_image=UnsplashUrls(thumb=null, small=https://images.unsplash.com/profile-1597331280483-dacd114a4901image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32, medium=https://images.unsplash.com/profile-1597331280483-dacd114a4901image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64, regular=null, large=https://images.unsplash.com/profile-1597331280483-dacd114a4901image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128, full=null, raw=null), links=UnsplashLinks(self=https://api.unsplash.com/users/martberrios, html=https://unsplash.com/@martberrios, photos=https://api.unsplash.com/users/martberrios/photos, likes=https://api.unsplash.com/users/martberrios/likes, portfolio=https://api.unsplash.com/users/martberrios/portfolio, download=null, download_location=null)))"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(mLayoutInflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        //juan
        val _infoPhotoView: infoPhotoView = infoPhotoView.getInstance()
        // item
        val photo = mListOfPhotos[position]
        // image background
        holder.itemView.setBackgroundColor(Color.parseColor(photo.color))
        // loading the photo
        Picasso.get().load(photo.urls.small)
            .into(holder.imageView)

        val image = (holder.imageView.getDrawable() as BitmapDrawable).bitmap
        val simage = encodeImage(image)

        val _infoLogin = infoLogin()

        _infoPhotoView.value = simage


        try {

            var _infoContext = infoContext()
            mContext = _infoContext._context

            val sfileName = "ezmovil_" +getCurrentDate() + ".jpg"

            DownloadAndSaveImageTask(_infoContext._context).execute(photo.urls.regular, sfileName)
            //DownloadAndSaveImageTask(this).execute("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg")


            manager = DataBaseManager(mContext)
            this.manager!!.insert_gallery(_infoLogin.loginUser, _infoLogin.loginPass,
                    _infoPhotoView.value, sfileName)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            // optional finally block
        }
    }

    override fun getItemCount(): Int {
        return mListOfPhotos.size
    }

    fun setListOfPhotos(listOfPhotos: ArrayList<UnsplashPhoto>?) {
        if (listOfPhotos != null) {
            mListOfPhotos = listOfPhotos
            notifyDataSetChanged()
        }
    }

    /**
     * UnsplashPhoto view holder.
     */
    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.item_photo_iv

    }

    fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun getCurrentDate():String{
        val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        return sdf.format(Date())
    }
}

class DownloadAndSaveImageTask(context: Context) : AsyncTask<String, Unit, Unit>() {
    private var mContext: WeakReference<Context> = WeakReference(context)


    override fun doInBackground(vararg params: String?) {
        val url = params[0]
        val requestOptions = RequestOptions().override(100)
            .downsample(DownsampleStrategy.CENTER_INSIDE)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        mContext.get()?.let {
            val bitmap = Glide.with(it)
                .asBitmap()
                .load(url)
                .apply(requestOptions)
                .submit()
                .get()

            try {
                var file = it.getDir("Images", Context.MODE_PRIVATE)
                file = File(file, params[1])
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
                out.flush()
                out.close()
                Log.i("ezmovil.net@gmail.com", "Image saved.")
            } catch (e: Exception) {
                Log.i("ezmovil.net@gmail.com", "Failed to save image.")
            }
        }
    }
}