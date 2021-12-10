package com.example.fulljson10.common

import com.example.fulljson10.retrofit.RetrofitClient
import com.example.fulljson10.retrofit.RetrofitServieces

//1. Обьект Common содержит в себе Первую часть URL для парсинга, которая не меняется.
object Common {
    private val BASE_URL = "https://imdb-api.com/en/API/"
//   2.  Создаем переменную retrofitServices, которая наследуется от Интерфейса созданного ранее. У нее есть метод get()
    val retrofitService: RetrofitServieces
//   3. К методу get() мы присваиваем RetrofitClient
//   4. А потом к RetrofitClient мы сетим метод getClient c параметром RetrofitServices::class.java
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)
}