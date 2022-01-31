package com.example.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fitchData()
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

            override fun onResponse(
                call: Call<ArrayList<DefultResponse>>,
                response: Response<ArrayList<DefultResponse>>
            ) {
                // toast data with index 0
                Toast.makeText(this@MainActivity, response.body()?.get(0)?.name, Toast.LENGTH_SHORT).show()
              //show all data
                var allData : ArrayList<DefultResponse>? = response.body()
                Toast.makeText(this@MainActivity, allData?.get(0)?.name, Toast.LENGTH_SHORT).show()


            }

            override fun onFailure(call: Call<ArrayList<DefultResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "غير متصل بالسيرفر ", Toast.LENGTH_SHORT).show()
            }

        })
    }
}