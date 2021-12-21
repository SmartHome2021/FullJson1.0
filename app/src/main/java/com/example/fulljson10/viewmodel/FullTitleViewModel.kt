package com.example.fulljson10.viewmodel

import TitleData
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.common.Common
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class FullTitleViewModel(state: SavedStateHandle): ViewModel() {
    private val retrofit : RetrofitServieces = Common.retrofitService

    var resultFullTitle = MutableStateFlow<TitleData?>(null)



    init{
        viewModelScope.launch {
            kotlin.runCatching{ withContext(Dispatchers.IO) { state.get<String>("s")?.let { retrofit.getTitleList(it) } }}
                .onSuccess {
                    resultFullTitle.value = it
                    Log.i("Response1", "Full Title Succes")
                }
                .onFailure { e ->
                    Log.e("Response", e.message, e)
                }
    }

}


}