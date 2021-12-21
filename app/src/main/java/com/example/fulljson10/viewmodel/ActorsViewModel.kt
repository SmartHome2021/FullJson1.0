package com.example.fulljson10.viewmodel

import TitleData
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ActorsViewModel(state: SavedStateHandle): ViewModel() {
    var resultActor = MutableStateFlow<TitleData?>(null)




    init {
        resultActor.value = state.get<TitleData>("t")
        Log.i("Response1", "Actor Succes")
    }



}