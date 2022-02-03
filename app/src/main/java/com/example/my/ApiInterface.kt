package com.example.my

import android.telecom.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    // notation
   @GET("posts")
    fun getData():retrofit2.Call<ArrayList<DefultResponse>>

    @GET("posts/{id}")
     fun getUserPost(@Path("id")id :Int): retrofit2.Call<DefultResponse>

    @GET("comments/")
    fun getPostByQuery(@Query("postId")postId :Int):retrofit2.Call<ArrayList<specificComment>>

    @FormUrlEncoded
    @POST("https://jsonplaceholder.typicode.com/posts")
    fun addingPost(
        @Field("userId") userId :Int,
        @Field("title") title :String,
        @Field("body") body :String, ) : retrofit2.Call<PostUserPost>

    //fun addPost(@Body postOfUser : DefultResponse ) : retrofit2.Call<PostUserPost>

}