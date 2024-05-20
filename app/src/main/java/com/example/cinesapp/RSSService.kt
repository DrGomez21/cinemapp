package com.example.cinesapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RSSService {
    @GET("v1/api.json?rss_url=http%3A%2F%2Fcines.com.py%2Ffeeds%2Fcines.rss&api_key=lquocfv8t6vxruewcsca05rsl9nlbsjxgb3sp6aj&count=25")
    suspend fun getCartelera(): Response<DataModel>
}