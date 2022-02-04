package com.siuzannasmolianinova.houseofapps.domain

import android.app.Application
import com.siuzannasmolianinova.houseofapps.data.Api
import com.siuzannasmolianinova.houseofapps.data.entity.Album
import com.siuzannasmolianinova.houseofapps.data.entity.Comment
import com.siuzannasmolianinova.houseofapps.data.entity.Photo
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface Repository {
    suspend fun loadPosts(): List<Post>
    suspend fun loadComments(postId: Int): List<Comment>
    suspend fun loadAlbums(): List<Album>
    suspend fun loadPhotos(albumId: Int): List<Photo>

    class RepositoryImpl @Inject
    constructor(
        private val networkApi: Api,
        private val context: Application
    ) : Repository {

        override suspend fun loadPosts(): List<Post> = withContext(Dispatchers.IO) {
            networkApi.loadPosts()
        }

        override suspend fun loadComments(postId: Int): List<Comment> =
            withContext(Dispatchers.IO) {
                networkApi.loadComments(postId.toString())
            }

        override suspend fun loadAlbums(): List<Album> = withContext(Dispatchers.IO) {
            networkApi.loadAlbums()
        }

        override suspend fun loadPhotos(albumId: Int): List<Photo> = withContext(Dispatchers.IO) {
            networkApi.loadPhotos(albumId.toString())
        }
    }
}
