package com.example.fulljson10.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fulljson10.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel(){
    var favorite250 = MutableStateFlow<List<Film>?>(null)

}