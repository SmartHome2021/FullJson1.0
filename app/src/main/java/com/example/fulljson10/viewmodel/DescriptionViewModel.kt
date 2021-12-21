package com.example.fulljson10.viewmodel

import TitleData
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


class DescriptionViewModel(state: SavedStateHandle): ViewModel() {

    val resultDescription = MutableStateFlow<TitleData?>(null)


    init {
        resultDescription.value = state.get<TitleData>("t")
        Log.i("Response1", "Description Succes")
    }

}