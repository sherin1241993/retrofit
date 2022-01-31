package com.example.my

import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    // notation
   @GET("comments")
    fun getData():retrofit2.Call<ArrayList<DefultResponse>>


}