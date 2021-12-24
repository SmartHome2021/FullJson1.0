package com.example.fulljson10.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao() : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getDatabase(context: Context): FilmDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
                    "film_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
