package com.siuzannasmolianinova.houseofapps.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siuzannasmolianinova.houseofapps.data.entity.Comment
import com.siuzannasmolianinova.houseofapps.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    fun loadComments(postId: Long) {
        viewModelScope.launch {
            try {
                _comments.postValue(repository.loadComments(postId))
            } catch (t: Throwable) {
                Timber.e(t)
                _comments.postValue(emptyList())
            }
        }
    }
}
