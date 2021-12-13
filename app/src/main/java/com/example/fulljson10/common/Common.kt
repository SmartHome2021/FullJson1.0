package com.example.fulljson10.common

import com.example.fulljson10.retrofit.RetrofitClient
import com.example.fulljson10.retrofit.RetrofitServieces

object Common {
    private val BASE_URL = "https://imdb-api.com/en/API/"
    val retrofitService: RetrofitServieces
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)
}