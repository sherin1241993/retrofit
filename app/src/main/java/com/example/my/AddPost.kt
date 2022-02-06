package com.example.my

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_post.*
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
            override fun onResponse(call: Call<PostUserPost>, response: Response<PostUserPost>) {
                Toast.makeText(this@AddPost , response.body()?.title.toString(), Toast.LENGTH_SHORT).show()
                    tx_show_userId.text =  response.body()?.userId.toString()
                    tx_show_title.text =  response.body()?.title.toString()
                    tx_show_body.text =  response.body()?.body.toString()



            }

            override fun onFailure(call: Call<PostUserPost>, t: Throwable) {
                Toast.makeText(this@AddPost ,"غير قادر علي الاتصال بالسيرفر", Toast.LENGTH_SHORT).show()

            }

        })
    }
}