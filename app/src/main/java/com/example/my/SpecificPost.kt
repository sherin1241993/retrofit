package com.example.my

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.specific_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpecificPost :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specific_post)

        bt_show_post.setOnClickListener {
            fitchPostWithPath(edit_enter_id.text.toString().toInt())
        }

    }



    fun fitchPostWithPath(postId : Int) {

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
        val call = Api.getPostById(id= postId)
        var item = call.enqueue(object : Callback<DefultResponse> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<DefultResponse>,
                response: Response<DefultResponse>
            ) {
                if (response.isSuccessful && response.body()!=null ) {

                    tx_post_userId.text = "userId :" + response.body()?.userId.toString()
                    tx_post_title.text = "title :"+ response.body()?.title.toString()
                    tx_post_body.text = "body : "+ response.body()?.body.toString()


                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response.message()}")
                    Toast.makeText(this@SpecificPost, response.message(), Toast.LENGTH_SHORT)
                        .show()

                }
            }

            override fun onFailure(call: Call<DefultResponse>, t: Throwable) {
                Toast.makeText(this@SpecificPost, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
                tx_post_userId.setText(t.message)
            }

        })




    }
}