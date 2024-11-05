package com.example.assignment3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/" // Base URL for the API

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Use the appropriate converter
            .build()
    }

    val api: KinopoiskApi by lazy {
        retrofit.create(KinopoiskApi::class.java)
    }
}




