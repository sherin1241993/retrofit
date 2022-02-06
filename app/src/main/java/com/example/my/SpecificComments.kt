package com.example.my

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recycler_posts.*
import kotlinx.android.synthetic.main.recycler_specific__comments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpecificComments : AppCompatActivity(){
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_specific__comments)



        bt_show_comments.setOnClickListener {
            recycler_comments.layoutManager = LinearLayoutManager(this ,LinearLayout.VERTICAL,false)
            fitchCommentWithQuery(edit_enter_postId.text.toString().toInt())
        }

    }


    fun fitchCommentWithQuery(postId : Int) {

        val Basic_Url = "https://jsonplaceholder.typicode.com/"

        //make a retrofit variable
        val retrofit = Retrofit.Builder()
            //base url
            .baseUrl(Basic_Url)
            //the type of converter
            .addConverterFactory(GsonConverterFactory.create())

            //.client(okHttpClient)
            .build()

        //retrofit fill body of interface not us
        //retrofit.create(ApiInterface::class.java)
        val Api: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call = Api.getCommentsByPostId(postId)
        var item = call.enqueue(object : Callback<ArrayList<specificComment>> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ArrayList<specificComment>>,
                response: Response<ArrayList<specificComment>>
            ) {
                if (response.isSuccessful && response.body()!=null ) {

                    var allData: ArrayList<specificComment>? = response.body()
                    recycler_comments.adapter = allData?.let { CustomComments(it) }

                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response.message()}")
                    Toast.makeText(this@SpecificComments, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ArrayList<specificComment>>, t: Throwable) {
                Toast.makeText(this@SpecificComments, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
                //textInput3.setText(t.message)
            }

        })




    }
}