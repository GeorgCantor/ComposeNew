package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.model.New
import com.example.domain.model.NewsRemoteKeys

@Database(
    entities = [New::class, NewsRemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}