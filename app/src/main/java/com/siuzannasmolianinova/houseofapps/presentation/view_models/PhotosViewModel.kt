package com.siuzannasmolianinova.houseofapps.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siuzannasmolianinova.houseofapps.data.entity.Photo
import com.siuzannasmolianinova.houseofapps.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    fun loadPhotos(albumId: Long) {
        viewModelScope.launch {
            try {
                _photos.postValue(repository.loadPhotos(albumId))
            } catch (t: Throwable) {
                Timber.e(t)
                _photos.postValue(emptyList())
            }
        }
    }
}
