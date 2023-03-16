package com.example.compose_movieapp.data.database

import android.content.Context
import androidx.room.Room

fun provideMovieDB(context: Context) =
    Room.databaseBuilder(context, MovieDatabase::class.java, MovieDatabase.DB_NAME).build()

fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()