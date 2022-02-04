package com.siuzannasmolianinova.houseofapps.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import com.siuzannasmolianinova.houseofapps.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    fun loadPosts() {
        viewModelScope.launch {
            try {
                _posts.postValue(repository.loadPosts())
            } catch (t: Throwable) {
                _error.postValue(t)
                _posts.postValue(emptyList())
            }
        }
    }
}
