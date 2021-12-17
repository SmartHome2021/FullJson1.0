package com.example.fulljson10.model

import TitleData
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.common.Common
import com.example.fulljson10.retrofit.RetrofitServieces
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class FullTitleViewModel(): ViewModel() {
    private val retrofit : RetrofitServieces = Common.retrofitService

    var resultFullTitle = MutableStateFlow<TitleData?>(null)
    lateinit var xxx: String


fun load(id: String){
    viewModelScope.launch {
        kotlin.runCatching{ withContext(Dispatchers.IO) {retrofit.getTitleList(id)} }
            .onSuccess {
                resultFullTitle.value = it
            }
            .onFailure { e ->
                Log.e("Response", e.message, e)
            }
    }


}


}