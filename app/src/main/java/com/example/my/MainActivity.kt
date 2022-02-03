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
        fitchData()
        fitchPostWithPath()
        fitchCommentWithQuery()
        addPost()

        but_next.setOnClickListener {
            var intent = Intent(this , Recyclerposts  ::class.java)
            startActivity(intent)
        }

    }


    fun fitchData() {

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
        val call = Api.getData()
        var item = call.enqueue(object : Callback<ArrayList<DefultResponse>> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ArrayList<DefultResponse>>,
                response: Response<ArrayList<DefultResponse>>
            ) {
                if (response.isSuccessful && response.body()!=null ) {

                   textInput.text="title of first post is"+ response.body()?.get(0)?.title.toString()

                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response.message()}")
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DefultResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
            }

        })




        }




    fun fitchPostWithPath() {

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
        val call = Api.getUserPost(id= 5)
        var item = call.enqueue(object : Callback<DefultResponse> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<DefultResponse>,
                response: Response<DefultResponse>
            ) {
                if (response.isSuccessful && response.body()!=null ) {

                    textInput2.text = "body of id = 5 is "+ response.body()?.body.toString()
                    // toast data with index 0
                   // Toast.makeText(this@MainActivity, response.body()?.body, Toast.LENGTH_SHORT).show()


                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response.message()}")
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<DefultResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
                textInput2.setText(t.message)
            }

        })




    }



    fun fitchCommentWithQuery() {

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
        val call = Api.getPostByQuery(postId= 1)
        var item = call.enqueue(object : Callback<ArrayList<specificComment>>{

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ArrayList<specificComment>>,
                response: Response<ArrayList<specificComment>>
            ) {
                if (response.isSuccessful && response.body()!=null ) {

                    textInput3.text= "body f comment post id = 1 is "+response.body()?.get(0)?.body.toString()


                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response.message()}")
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ArrayList<specificComment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
                textInput3.setText(t.message)
            }

        })




    }

    fun addPost() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiInterface = retrofit.create(ApiInterface::class.java)
        //function at serviceapi
        val call = api.addingPost(userId = 1,title = "sherin post a post", body = " sherin khaled mahmoud abd el rahman using retrofit ")
        call.enqueue(object : Callback<PostUserPost> {
            override fun onResponse(call: Call<PostUserPost>, response: Response<PostUserPost>) {
                Toast.makeText(this@MainActivity, response.body()?.title.toString(), Toast.LENGTH_SHORT).show()
                textInput4.text =  response.body()?.title.toString()
            }

            override fun onFailure(call: Call<PostUserPost>, t: Throwable) {
               Toast.makeText(this@MainActivity ,"غير قادر علي الاتصال بالسيرفر", Toast.LENGTH_SHORT).show()

            }

        })
    }

}