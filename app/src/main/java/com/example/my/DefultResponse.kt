package com.example.my

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefultResponse(@Json(name = "userId")
                          val userId: Int?,
                          @Json (name = "id")
                          val id:Int?,
                          @Json (name = "title")
                          val title:String?,
                          @Json (name = "body")
                          val body : String?)
data class PostUserPost(@SerializedName ("userId") val userId: Int, @SerializedName ("title") val title:String, @SerializedName ("body")val body : String)

data class specificComment( @SerializedName ("postId") val postId: Int, @SerializedName ("id")val id:Int, @SerializedName ("name") val name:String,@SerializedName ("email") val email:String, @SerializedName ("body")val body : String)
