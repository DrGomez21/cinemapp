package com.example.cinesapp

import com.google.gson.annotations.SerializedName

data class DataModel (
    @SerializedName("status") var status : String,
    @SerializedName("feed") var feed : Feed,
    @SerializedName("items") var peliculas : List<Item>
)