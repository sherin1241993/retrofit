package com.example.my

import com.google.gson.annotations.SerializedName

data class DefultResponse( @SerializedName ("userId") val userId: Int, @SerializedName ("id")val id:Int, @SerializedName ("title") val title:String, @SerializedName ("body")val body : String)
data class PostUserPost(@SerializedName ("userId") val userId: Int, @SerializedName ("title") val title:String, @SerializedName ("body")val body : String)

data class specificComment( @SerializedName ("postId") val postId: Int, @SerializedName ("id")val id:Int, @SerializedName ("name") val name:String,@SerializedName ("email") val email:String, @SerializedName ("body")val body : String)
