package com.example.flash.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://training-uploads.internshala.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        ScalarsConverterFactory.create()
    )
    .baseUrl(BASE_URL)
    .build()

interface FlashApiService {
    @GET("android/grocery_delivery_app/items.json")
    suspend fun getItems():String
}

object FlashApi {
    val retrofitService : FlashApiService by lazy {
        retrofit.create(
            FlashApiService::class.java
        )
    }
}