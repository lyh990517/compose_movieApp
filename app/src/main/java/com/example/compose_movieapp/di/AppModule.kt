package com.example.compose_movieapp.di

import android.content.Context
import com.example.compose_movieapp.data.api.ApiHelper
import com.example.compose_movieapp.data.api.MovieService
import com.example.compose_movieapp.data.database.MovieDatabase
import com.example.compose_movieapp.data.database.provideMovieDB
import com.example.compose_movieapp.data.database.provideMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi() = ApiHelper.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = provideMovieDB(context)

    @Singleton
    @Provides
    fun provideDao(movieDatabase: MovieDatabase) = provideMovieDao(movieDatabase)
}