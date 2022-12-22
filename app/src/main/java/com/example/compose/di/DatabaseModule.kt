package com.example.compose.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, NewsDatabase::class.java, "news_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.newsDao()

    @Provides
    fun provideRemoteKeysDao(database: NewsDatabase) = database.remoteKeysDao()
}