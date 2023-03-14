package com.example.compose_movieapp.di

import com.example.compose_movieapp.api.ApiHelper
import com.example.compose_movieapp.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi() = ApiHelper.create(MovieService::class.java)
}