package com.siuzannasmolianinova.houseofapps.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import com.siuzannasmolianinova.houseofapps.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun fetchPostsLiveData(): LiveData<PagingData<Post>> {
        return repository.letPostsLiveData()
    }
}
