package com.example.fulljson10.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao() : FavoriteDao

    companion object{
        private var INSTANCE: FilmDatabase? = null
        fun getInstance(context: Context): FilmDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context, FilmDatabase::class.java, "film_test"
                ).build()
            }
            return INSTANCE as FilmDatabase
        }
    }
}
