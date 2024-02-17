package com.test.pexelsapp.presentation.viewmodelfactory

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.test.domain.models.ImageResponse
import com.test.data.repository.ImageRepositoryImpl
import com.test.domain.models.Photo
import com.test.domain.use_cases.LoadCuratedPhotosUseCase
import com.test.domain.use_cases.LoadFeaturedCollectionsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val imageRepository: ImageRepositoryImpl,
    private val loadFeaturedCollectionsUseCase: LoadFeaturedCollectionsUseCase,
    private val loadCuratedPhotosUseCase: LoadCuratedPhotosUseCase
) : ViewModel() {

    var imageList: MutableLiveData<Response<ImageResponse>>

    private val _featuredCollectionNames = MutableLiveData<List<String>>()
    val featuredCollectionNames: LiveData<List<String>> get() = _featuredCollectionNames

    init {
        imageList = MutableLiveData()
        getFeaturedCollectionNames()
        getCuratedPhotos()
    }


    private fun getFeaturedCollectionNames() {
        viewModelScope.launch {
            val names = loadFeaturedCollectionsUseCase.execute()
            _featuredCollectionNames.postValue(names)
        }
    }


    fun getCuratedPhotos() {
        viewModelScope.launch {
            val response = loadCuratedPhotosUseCase.execute()
            imageList.postValue(response)
        }
    }


    fun getImages(query: String) {
        viewModelScope.launch {
            val response = imageRepository.getImages(query)
            imageList.postValue(response)
        }
    }
}