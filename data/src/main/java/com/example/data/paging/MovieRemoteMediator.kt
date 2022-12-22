package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.BuildConfig
import com.example.data.api.NewsApi
import com.example.data.db.NewsDatabase
import com.example.domain.model.New
import com.example.domain.model.NewsRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, New>() {
    private val newsDao = newsDatabase.newsDao()
    private val remoteKeysDao = newsDatabase.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, New>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> getRemoteKeyClosestToCurrentPosition(state)?.nextPage?.minus(1) ?: 1
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(remoteKeys != null)
                    nextPage
                }
            }
            val response = newsApi.getNews(apiKey = BuildConfig.API_KEY, page = page)
            var endOfPaginationReached = false

            if (response.isSuccessful) {
                val movieList = response.body()
                endOfPaginationReached = movieList == null
                movieList?.let {
                    newsDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            newsDao.deleteAllNews()
                            remoteKeysDao.deleteAllMovieRemoteKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int
                        movieList.page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }
                        val keys = movieList.articles.map { movie ->
                            NewsRemoteKeys(
                                id = movie.id.toInt(),
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        remoteKeysDao.addAllMovieRemoteKeys(newsRemoteKeys = keys)
                        newsDao.addNews(aNews = movieList.articles.map {
                            New(
                                id = it.id.toInt(),
                                overview = it.description,
                                posterPath = it.urlToImage,
                                title = it.title,
                                releaseDate = it.publishedAt
                            )
                        })
                    }
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, New>) =
        state.anchorPosition?.let { state.closestItemToPosition(it)?.id?.let { remoteKeysDao.getRemoteKeys(it) } }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, New>) =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { remoteKeysDao.getRemoteKeys(it.id) }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, New>)=
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { remoteKeysDao.getRemoteKeys(it.id) }
}