package com.example.compose_movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compose_movieapp.data.BoxOffice

@Database(entities = [BoxOffice::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    companion object{
        val DB_NAME = "movie.db"
    }
}