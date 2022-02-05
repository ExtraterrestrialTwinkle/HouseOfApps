package com.siuzannasmolianinova.houseofapps.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.siuzannasmolianinova.houseofapps.data.Api
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import com.siuzannasmolianinova.houseofapps.domain.Constant.DEFAULT_PAGE
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class PostsApiSource(private val networkApi: Api) :
    PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        Timber.d("load")
        val page = params.key ?: DEFAULT_PAGE
        return try {
            val response = networkApi.loadPosts(page, params.loadSize)
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition
    }
}
