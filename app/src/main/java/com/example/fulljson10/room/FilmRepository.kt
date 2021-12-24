package com.example.fulljson10.room

import androidx.lifecycle.LiveData

class FilmRepository(private val filmDao: FavoriteDao) {

    val readAllData: LiveData<List<FavoriteEntity>> = filmDao.readAll()

    suspend fun addFilm(film: FavoriteEntity){
        filmDao.insert(film)
    }
}