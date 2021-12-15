package com.example.fulljson10.model

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

//Класс описывающий структуру нашего data class.
class FilmResponse(
    val items: List<Film>
)