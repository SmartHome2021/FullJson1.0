package com.example.fulljson10.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.Room
import com.example.fulljson10.adapter.MyMovieAdapter
import com.example.fulljson10.common.Common
import com.example.fulljson10.model.Film
import com.example.fulljson10.retrofit.RetrofitServieces
import com.example.fulljson10.room.FavoriteEntity
import com.example.fulljson10.room.FilmDatabase
import com.example.fulljson10.room.FilmRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow


class Top250ViewModel(application: Application): ViewModel() {
    private var retrofit : RetrofitServieces = Common.retrofitService

    lateinit var readAllData: LiveData<List<FavoriteEntity>>
    lateinit var repository: FilmRepository

    var result250 = MutableStateFlow<List<Film>?>(null)
    var itemCount = MutableStateFlow<Int?>(0)
    var timeCount = MutableStateFlow<Long?>(0L)

    var searchArray: List<Film>? = null


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

                    val filmDao = FilmDatabase.getDatabase(application).filmDao()
                    repository = FilmRepository(filmDao)
                    readAllData = repository.readAllData
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

    fun addFilm(film: FavoriteEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFilm(film)
        }
    }


}







