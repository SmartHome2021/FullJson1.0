package com.example.fulljson10.retrofit

import TitleData
import android.telecom.Call
import com.example.fulljson10.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path
//1. Создаем пакет Interface в него мы кладем Interface и называем RetrofitServieces
interface RetrofitServieces {
//2. Создаем Get запрос в скобках пишем кавычки, а в кавычках указывает ветку с которой будем парсить данные
//Это 2я часть ссылки, которая может меняться.
    @GET("Top250Movies/k_1liv8krd")
//    k_1liv8krd Мой
//    k_ft56zq4q Avenger
//    k_snggbqyh Zebra

//3. Cоздаем функцию getMovieList, которая должна будет возвращать Call типа заданного у нас в Response файле,
// либо напрямую в Data Class ,зависит от конструкции json.
    fun getMovieList (): retrofit2.Call<FilmResponse>

// 3.1   Аналогично предыдущей. Из изменений, добавлен {id}, который является меняющейся переменной.
//    При добавлении в url меняющейся переменной добавляется запрос @Path
    @GET("Title/k_1liv8krd/{id}/Images")
    fun getTitleList (@Path("id") id: String): retrofit2.Call<TitleData>

}