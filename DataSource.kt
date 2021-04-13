package com.codingwithmitch.kotlinrecyclerviewexample

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.codingwithmitch.kotlinrecyclerviewexample.models.BlogPost
import com.squareup.picasso.Picasso
import com.unsplash.pickerandroid.example.dbase.DataBaseManager
import com.unsplash.pickerandroid.example.info.infoContext
import java.util.Base64.getDecoder

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
                                    position
                                )
                            )
                        }
                    } while (_images.moveToNext())
                }
            }

            /*val list = ArrayList<BlogPost>()
            list.add(
                BlogPost(
                    "Congratulations!",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    "Sally"
                )
            )
            list.add(
                BlogPost(
                    "Time to Build a Kotlin App!",
                    "The REST API course is complete. You can find the videos here: https://codingwithmitch.com/courses/build-a-rest-api/.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/time_to_build_a_kotlin_app.png",
                    "mitch"
                )
            )

            list.add(
                BlogPost(
                    "Interviewing a Web Developer and YouTuber",
                    "Justin has been producing online courses for YouTube, Udemy, and his website CodingForEntrepreneurs.com for over 5 years.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/coding_for_entrepreneurs.png",
                    "John"
                )
            )
            list.add(
                BlogPost(
                    "Freelance Android Developer (Vasiliy Zukanov)",
                    "Vasiliy has been a freelance android developer for several years. He also has some of the best android development courses I've had the pleasure of taking on Udemy.com.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_vasiliy_zukanov.png",
                    "Steven"
                )
            )
            list.add(
                BlogPost(
                    "Freelance Android Developer, Donn Felker",
                    "Freelancing as an Android developer with Donn Felker.\\r\\n\\r\\nDonn is also:\\r\\n\\r\\n1) Founder of caster.io\\r\\n\\r\\n2) Co-host of the fragmented podcast (fragmentedpodcast.com).",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_donn_felker.png",
                    "Richelle"
                )
            )
            list.add(
                BlogPost(
                    "Work Life Balance for Software Developers",
                    "What kind of hobbies do software developers have? It sounds like many software developers don't have a lot of hobbies and choose to focus on work. Is that a good idea?",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/work_life_balance.png",
                    "Jessica"
                )
            )
            list.add(
                BlogPost(
                    "Full Stack Web Developer - Nicholas Olsen",
                    "In this podcast I interviewed the Fullsnack Developer (AKA Nicholas Olsen).\\r\\n\\r\\nNicholas is many things. What I mean by that is, he's good at many things.\\r\\n\\r\\n1. He’s an entrepreneur\\r\\n\\r\\n2. Web developer\\r\\n\\r\\n3. Artist\\r\\n\\r\\n4. Graphic designer\\r\\n\\r\\n5. Musician (drums)\\r\\n\\r\\n6. Professional BodyBuilder.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/fullsnack_developer.png",
                    "Guy"
                )
            )
            list.add(
                BlogPost(
                    "Javascript Expert - Wes Bos",
                    "Interviewing a web developer, javascript expert, entrepreneur, freelancer, podcaster, and much more.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/javascript_expert_wes_bos.png",
                    "Ruby"
                )
            )
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