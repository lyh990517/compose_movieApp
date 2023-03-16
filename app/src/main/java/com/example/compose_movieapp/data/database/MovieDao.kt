package com.example.compose_movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.compose_movieapp.data.BoxOffice

@Dao
interface MovieDao {
    @Query("SELECT * FROM BoxOffice")
    suspend fun getAll() : List<BoxOffice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(boxOffice: BoxOffice)

    @Query("SELECT* FROM BoxOffice WHERE movieNm =:name")
    suspend fun getOne(name: String) : BoxOffice
}