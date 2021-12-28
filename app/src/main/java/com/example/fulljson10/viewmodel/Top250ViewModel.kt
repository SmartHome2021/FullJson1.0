package com.example.fulljson10.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.Top250Fragment
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.Film
import com.example.fulljson10.retrofit.RetrofitServieces
import com.example.fulljson10.room.FavoriteEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow



class Top250ViewModel(): ViewModel(){
    private var retrofit : RetrofitServieces = Common.retrofitService
    var result250 = MutableStateFlow<List<Film>?>(null)
    var itemCount = MutableStateFlow<Int?>(0)
    var timeCount = MutableStateFlow<Long?>(0L)
    var searchArray: List<Film>? = null
    var favoriteArray: Film? = null


    init {
        viewModelScope.launch {
            delay(1000)
            kotlin.runCatching { withContext(Dispatchers.IO) { retrofit.getMovieList() } }
                .onSuccess {
                    val x = System.currentTimeMillis() //Начало операции
                    result250.value = it.items
                    searchArray = it.items
                    itemCount.value = result250.value!!.size // Кол-во обьектов
                    val y = System.currentTimeMillis() - x // Конец операции
                    timeCount.value = y
                    Log.i("Response1", "Top250 Succes")
                }
                .onFailure { e ->
                    Log.e("Response", e.message, e)
                }
        }
    }



    fun clear(){
        result250.value = searchArray
        Log.d("Response1", "Clear Succes")
    }

    fun search(s: String){
        val x = searchArray?.filter {it.title.contains(s, ignoreCase = true)}
        result250.value = x
    }

    fun isFavorite(f: List<FavoriteEntity>){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var temp = ""
                f.onEach{ i ->
                    temp = i.filmId
                    val isContains = result250.value.toString().contains(temp)
                    if (isContains){
                        favoriteArray?.isFavorite = isContains
                    } else favoriteArray?.isFavorite = false
                }
            }
        }
    }
}







