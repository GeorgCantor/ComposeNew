package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.model.Movie
import com.example.domain.model.MovieRemoteKeys

@Database(
    entities = [Movie::class, MovieRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
}