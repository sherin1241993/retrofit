package com.example.my

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but_showAllPots.setOnClickListener {
            var intent = Intent(this , Recyclerposts  ::class.java)
            startActivity(intent)
        }
        but_showOnlyOnePost.setOnClickListener {
            var intent2 = Intent(this , SpecificPost  ::class.java)
            startActivity(intent2)
        }
        but_show_comment_by_postId.setOnClickListener {
            var intent3 = Intent(this , SpecificComments  ::class.java)
            startActivity(intent3)

        }
        bt_add_post.setOnClickListener {
            var intent4 = Intent(this , AddPost  ::class.java)
            startActivity(intent4)
        }

    }




}