package com.example.fulljson10.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.common.Common
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow


class Top250ViewModel: ViewModel() {
    private var retrofit : RetrofitServieces = Common.retrofitService

    var result250 = MutableStateFlow<List<Film>?>(null)
    var itemCount = MutableStateFlow<Int?>(0)
    var timeCount = MutableStateFlow<Long?>(0L)

    init {
        viewModelScope.launch {
            delay(2000)
            kotlin.runCatching { withContext(Dispatchers.IO) { retrofit.getMovieList() } }
                .onSuccess {
                    val x = System.currentTimeMillis() //Начало операции
                    result250.value = it.items
                    itemCount.value = result250.value!!.size // Кол-во обьектов
                    val y = System.currentTimeMillis() - x // Конец операции
                    timeCount.value = y
                }
                .onFailure { e ->
                    Log.e("Response", e.message, e)
                }
        }
    }

}






