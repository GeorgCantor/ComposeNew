package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.api.MovieApi
import com.example.data.db.MovieDatabase
import com.example.domain.model.Movie
import com.example.domain.model.MovieRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, Movie>() {
    private val movieDao = movieDatabase.movieDao()
    private val movieRemoteKeysDao = movieDatabase.movieRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
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
            val response = movieApi.getPopularMovies(apiKey = BuildConfig.API_KEY, page = page)
            var endOfPaginationReached = false

            if (response.isSuccessful) {
                val movieList = response.body()
                endOfPaginationReached = movieList == null
                movieList?.let {
                    movieDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            movieDao.deleteAllMovies()
                            movieRemoteKeysDao.deleteAllMovieRemoteKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int
                        movieList.page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }
                        val keys = movieList.movies.map { movie ->
                            MovieRemoteKeys(
                                id = movie.movieId,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        movieRemoteKeysDao.addAllMovieRemoteKeys(movieRemoteKeys = keys)
                        movieDao.addMovies(movies = movieList.movies)
                    }
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Movie>) =
        state.anchorPosition?.let { state.closestItemToPosition(it)?.movieId?.let { movieRemoteKeysDao.getMovieRemoteKeys(it) } }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Movie>) =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movieRemoteKeysDao.getMovieRemoteKeys(it.movieId) }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Movie>)=
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movieRemoteKeysDao.getMovieRemoteKeys(it.movieId) }
}