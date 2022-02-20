package com.example.my

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadLocate()

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
        bt_change_language.setOnClickListener {
            changeLanguage()
        }

    }

    private fun changeLanguage() {
        var listItem = arrayOf("عربي","English")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItem,-1){dialog ,which->
            if (which == 0){
                setLocate("ar")
                recreate()
            }
            else if (which == 1){
                setLocate("Default Value")
                recreate()
            }
       dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mBuilder.show()
    }

    @SuppressLint("CommitPrefEdits")
    private fun setLocate(Lang: String) {
    val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config , baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings",Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()
    }

private fun loadLocate(){
    val sharedPreferences = getSharedPreferences("Settings",Activity.MODE_PRIVATE)
    val language = sharedPreferences.getString("My_Lang","")
    if (language != null) {
        setLocate(language)
    }
    }
  }