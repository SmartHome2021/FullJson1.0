package com.example.fulljson10.retrofit

import TitleData
import android.telecom.Call
import com.example.fulljson10.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path
interface  RetrofitServieces {
    @GET("Top250Movies/k_1liv8krd")
//    k_1liv8krd Мой
//    k_ft56zq4q Avenger
//    k_snggbqyh Zebra

    suspend fun getMovieList (): FilmResponse

    @GET("Title/k_1liv8krd/{id}/Images")
    suspend fun getTitleList (@Path("id") id: String): TitleData


}