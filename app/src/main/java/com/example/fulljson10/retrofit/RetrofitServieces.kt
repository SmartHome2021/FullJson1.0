package com.example.fulljson10.retrofit

import TitleData
import android.telecom.Call
import com.example.fulljson10.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path
interface  RetrofitServieces {
    @GET("Top250Movies/k_snggbqyh")
//    k_1liv8krd Мой
//    k_ft56zq4q Avenger
//    k_snggbqyh Zebra

    suspend fun getMovieList (): FilmResponse

    @GET("Title/k_snggbqyh/{id}/Images")
    suspend fun getTitleList (@Path("id") id: String): TitleData


}