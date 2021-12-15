package com.example.fulljson10.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.common.Common
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow


class Top250Model(): ViewModel() {
    private var retrofit : RetrofitServieces = Common.retrofitService


    var result250 = MutableStateFlow<List<Film>?>(null)
    var conterItem = 0



    init {
        viewModelScope.launch {
            kotlin.runCatching { withContext(Dispatchers.IO) { retrofit.getMovieList() } }

                .onSuccess {
                    val result = retrofit.getMovieList()
                    result250.value = result.items
                    val test1 = result250.value
                    if (test1 != null) {
                        while (conterItem in test1.indices){
                            conterItem++

                        }
                    }
                }

                .onFailure { e ->
                    Log.e("Response", e.message, e)
                }
        }
    }
}






