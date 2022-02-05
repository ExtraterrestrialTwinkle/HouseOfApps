package com.siuzannasmolianinova.houseofapps.domain

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.siuzannasmolianinova.houseofapps.data.Api
import com.siuzannasmolianinova.houseofapps.data.entity.Album
import com.siuzannasmolianinova.houseofapps.data.entity.Comment
import com.siuzannasmolianinova.houseofapps.data.entity.Photo
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import com.siuzannasmolianinova.houseofapps.domain.Constant.DEFAULT_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface Repository {
    fun letPostsLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Post>>
    fun getDefaultPageConfig(): PagingConfig
    suspend fun loadComments(postId: Long): List<Comment>
    suspend fun loadAlbums(): List<Album>
    suspend fun loadPhotos(albumId: Long): List<Photo>

    class RepositoryImpl @Inject
    constructor(
        private val networkApi: Api
    ) : Repository {

        override fun letPostsLiveData(pagingConfig: PagingConfig): LiveData<PagingData<Post>> {
            return Pager(
                config = pagingConfig,
                pagingSourceFactory = {
                    PostsApiSource(networkApi)
                }
            ).liveData
        }

        override fun getDefaultPageConfig(): PagingConfig {
            return PagingConfig(pageSize = DEFAULT_SIZE, enablePlaceholders = false)
        }

        override suspend fun loadComments(postId: Long): List<Comment> =
            withContext(Dispatchers.IO) {
                networkApi.loadComments(postId.toString())
            }

        override suspend fun loadAlbums(): List<Album> = withContext(Dispatchers.IO) {
            networkApi.loadAlbums()
        }

        override suspend fun loadPhotos(albumId: Long): List<Photo> = withContext(Dispatchers.IO) {
            networkApi.loadPhotos(albumId.toString())
        }
    }
}
