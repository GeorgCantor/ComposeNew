package com.example.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(aNews: List<New>)

    @Query("SELECT * FROM new")
    fun getAllMovies(): PagingSource<Int, New>

    @Query("SELECT * FROM new WHERE id = :id")
    fun getMovie(id: Int): Flow<New>

    @Query("DELETE FROM new")
    suspend fun deleteAllMovies()
}