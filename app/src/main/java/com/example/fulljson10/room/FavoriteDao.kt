package com.example.fulljson10.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.StateFlow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(filmId: FavoriteEntity )

    @Query("SELECT * FROM film_table ORDER BY filmId ASC")
    fun readAll(): LiveData<List<FavoriteEntity>>

}


