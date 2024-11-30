package com.example.appfinal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("user")
    fun getList(): Call<List<Item>>

    @POST("your-endpoint")
    fun createItem(@Body item: Item): Call<Item>
}
