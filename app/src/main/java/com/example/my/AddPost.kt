package com.example.my

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.android.synthetic.main.specific_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddPost : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        bt_show_add_post.setOnClickListener {
            progressBar3.visibility = View.VISIBLE

            addPost(ed_add_userId.text.toString().toInt(),ed_add_title.text.toString(),ed_add_body.text.toString())

        }


    }
    fun addPost(userId : Int , title : String , body : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiInterface = retrofit.create(ApiInterface::class.java)
        //function at serviceapi
        val call = api.addingPost(userId,title, body)
        call.enqueue(object : Callback<PostUserPost> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PostUserPost>, response: Response<PostUserPost>) {

                progressBar3.visibility = View.INVISIBLE

                tx_show_userId.text = "userId :"+ response.body()?.userId.toString()
                    tx_show_title.text = "Title"+ response.body()?.title.toString()
                    tx_show_body.text = "body"+ response.body()?.body.toString()



            }

            override fun onFailure(call: Call<PostUserPost>, t: Throwable) {
                Toast.makeText(this@AddPost ,"غير قادر علي الاتصال بالسيرفر", Toast.LENGTH_SHORT).show()

            }

        })
    }
}