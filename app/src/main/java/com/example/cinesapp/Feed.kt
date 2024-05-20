package com.example.cinesapp

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("url") var url : String,
    @SerializedName("title") var title : String,
    @SerializedName("link") var link : String,
    @SerializedName("author") var author : String,
    @SerializedName("description") var description : String,
    @SerializedName("image") var image : String
) {
}