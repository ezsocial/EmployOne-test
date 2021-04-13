package com.codingwithmitch.kotlinrecyclerviewexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.unsplash.pickerandroid.example.MainUnsplashActivity
import com.unsplash.pickerandroid.example.R
import com.unsplash.pickerandroid.example.dbase.DataBaseManager
import com.unsplash.pickerandroid.example.info.infoContext
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var manager: DataBaseManager? = null;
    private var mContext: Context? = null;

    private lateinit var blogAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this@MainActivity
        var _infoContext = infoContext()
        _infoContext._context = mContext


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Unsplash DEMO", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val intent = Intent(this@MainActivity, MainUnsplashActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this@MainActivity.startActivity(intent)

        }

        initRecyclerView()
        addDataSet()

        recycler_view.adapter = blogAdapter
        recycler_view.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(applicationContext, "clicked on " , Toast.LENGTH_SHORT).show()
               // var position  =recycler_view.adapter(position)
                val myposition = blogAdapter.getItems(position)
                mContext = this@MainActivity
                manager = DataBaseManager(mContext)
                manager!!.delete_imageDirectory(myposition.toString())

                initRecyclerView()
                addDataSet()
                blogAdapter.notifyDataSetChanged()

            }
        })

    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            blogAdapter = BlogRecyclerAdapter()
            adapter = blogAdapter

        }
    }

    override fun onRestart() {
        super.onRestart()
        initRecyclerView()
        addDataSet()

    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }

}























