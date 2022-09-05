package com.example.compose.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, MovieDatabase::class.java, "movies_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()

    @Provides
    fun provideMovieRemoteKeysDao(database: MovieDatabase) = database.movieRemoteKeysDao()
}