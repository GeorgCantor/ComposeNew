package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.NewsRemoteKeys

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM news_remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): NewsRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(newsRemoteKeys: List<NewsRemoteKeys>)

    @Query("DELETE FROM news_remote_keys")
    suspend fun deleteAllRemoteKeys()
}