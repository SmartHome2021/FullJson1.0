package com.example.fulljson10.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class FavoriteEntity(
    @PrimaryKey
    val filmId: String
)