package com.example.my

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recycler_posts.*
import kotlinx.android.synthetic.main.recycler_row_posts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class Recyclerposts : AppCompatActivity(){
    @SuppressLint("WrongConstant")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_posts)


        recycler_posts.layoutManager = LinearLayoutManager(this , LinearLayout.VERTICAL,false)
        //val adapter = CustomAdapter(ArrayList<DefultResponse>)

        fitchAllPosts()


    }

    fun fitchAllPosts() {

        val Basic_Url = "https://jsonplaceholder.typicode.com/"

        //make a retrofit variable
        val retrofit = Retrofit.Builder()
            //base url
            .baseUrl(Basic_Url)
            //the type of converter
            .addConverterFactory(MoshiConverterFactory.create())

            //.client(okHttpClient)
            .build()

        //retrofit fill body of interface not us
        //retrofit.create(ApiInterface::class.java)
        val Api: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call = Api.getData()
        var item = call.enqueue(object : Callback<ArrayList<DefultResponse>> {

            override fun onResponse(
                call: Call<ArrayList<DefultResponse>>,
                response2: Response<ArrayList<DefultResponse>>
            ) {
                if (response2.isSuccessful && response2!=null ) {

                    //show all data
                    var allData: ArrayList<DefultResponse>? = response2.body()
                    recycler_posts.adapter = allData?.let { CustomAdapter(it) }

                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response2.message()}")
                    Toast.makeText(this@Recyclerposts, "${response2.message()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ArrayList<DefultResponse>>, t: Throwable) {
                Toast.makeText(this@Recyclerposts, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
            }

        })




    }




    }


