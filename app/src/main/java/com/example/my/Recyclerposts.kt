package com.example.my

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recycler_posts.*
import kotlinx.android.synthetic.main.recycler_row_posts.*
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.CoroutineContext

class Recyclerposts : AppCompatActivity() {


    @DelicateCoroutinesApi
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_posts)




               recycler_posts.layoutManager =
                   LinearLayoutManager(this@Recyclerposts, LinearLayout.VERTICAL, false)
               //val adapter = CustomAdapter(ArrayList<DefultResponse>)

               fitchAllPosts()

    }

    //using retrofit coroutines

    @DelicateCoroutinesApi
    fun fitchAllPosts() {

        val Basic_Url = "https://jsonplaceholder.typicode.com/"

        //make a retrofit variable
        val retrofit = Retrofit.Builder()
            //base url
            .baseUrl(Basic_Url)
            //the type of converter
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(
                KotlinJsonAdapterFactory()).build()))

            //.client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
         //launching a new coroutine
        GlobalScope.launch (Dispatchers.IO) {
            val response = retrofit.getData()
            withContext(Dispatchers.Main){
            if (response.isSuccessful) {

                val allData: ArrayList<DefultResponse>? =
                    response.body() as ArrayList<DefultResponse>?
                recycler_posts.adapter = allData?.let { CustomAdapter(it) }
            }

        }
     }
    }








    /*
    //using retrofit
    fun fitchAllPosts() {

        val Basic_Url = "https://jsonplaceholder.typicode.com/"

        //make a retrofit variable
        val retrofit = Retrofit.Builder()
            //base url
            .baseUrl(Basic_Url)
            //the type of converter
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(
                KotlinJsonAdapterFactory()).build()))

            //.client(okHttpClient)
            .build()

        //retrofit fill body of interface not us
        //retrofit.create(ApiInterface::class.java)
        val Api: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call = Api.getData()
        var item = call.enqueue(object : Callback<List<DefultResponse>> {

            override fun onResponse(
                call: Call<List<DefultResponse>>,
                response2: Response<List<DefultResponse>>
            ) {
                if (response2.isSuccessful && response2!=null ) {

                    //show all data
                    var allData: ArrayList<DefultResponse>? = response2.body() as ArrayList<DefultResponse>?
                    recycler_posts.adapter = allData?.let { CustomAdapter(it) }

                }else {
                    Log.d("TAG", "getBalance: Error Response >>> ${response2.message()}")
                    Toast.makeText(this@Recyclerposts, "${response2.message()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<DefultResponse>>, t: Throwable) {
                Toast.makeText(this@Recyclerposts, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
            }

        })




    }

*/


    }


