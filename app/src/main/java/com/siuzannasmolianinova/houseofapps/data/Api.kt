package com.siuzannasmolianinova.houseofapps.data

import com.siuzannasmolianinova.houseofapps.data.entity.Album
import com.siuzannasmolianinova.houseofapps.data.entity.Comment
import com.siuzannasmolianinova.houseofapps.data.entity.Photo
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/posts?_start=0&_limit=20")
    suspend fun loadPosts(): List<Post>

    @GET("/comments")
    suspend fun loadComments(
        @Query("postId") postId: String
    ): List<Comment>

    @GET("/albums?_start=0&_limit=20")
    suspend fun loadAlbums(): List<Album>

    @GET("/photos")
    suspend fun loadPhotos(
        @Query("albumId") albumId: String
    ): List<Photo>
}
