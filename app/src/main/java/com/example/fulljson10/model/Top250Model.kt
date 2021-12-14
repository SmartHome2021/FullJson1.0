package com.example.fulljson10.model

import android.graphics.ColorSpace.get
import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.retrofit.RetrofitClient
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class Top250Model(private val retrofit: RetrofitServieces): ViewModel() {

    val result250 = MutableStateFlow<List<Film>?>(null)

    init {
        viewModelScope.launch {
            val result =  retrofit.getMovieList()

            this.coroutineContext:  = result
//
//            result250.emit(result)

        }
        }

    fun getFilm() = result250



    }

