package com.example.fulljson10.model

//Data Class на основе json файла (1 страница)
data class Film(
    val id: String,
    val rank: Int,
    val title: String,
    val fullTitle: String,
    val year: Int,
    val image: String,
    val crew: String,
    val imDbRating: Float,
    val imDbRatingCount: Long,
    val isFavorite: Boolean
)