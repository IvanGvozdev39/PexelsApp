package com.test.pexelsapp.presentation.viewmodelfactory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.models.images.ImageResponse
import com.test.data.repository.ImageRepositoryImpl
import com.test.domain.use_cases.LoadCuratedPhotosUseCase
import com.test.domain.use_cases.LoadFeaturedCollectionsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val imageRepository: ImageRepositoryImpl,
    private val loadFeaturedCollectionsUseCase: LoadFeaturedCollectionsUseCase,
    private val loadCuratedPhotosUseCase: LoadCuratedPhotosUseCase
) : ViewModel() {

    var imageList: MutableLiveData<Response<ImageResponse>> = MutableLiveData()

    var cleanImageRV: MutableLiveData<Int> = MutableLiveData(0)

//    var imageListFeaturedCollection: MutableLiveData<Response<CollectionMediaResponse>> = MutableLiveData()


    private val _featuredCollectionNames = MutableLiveData<List<com.test.domain.models.images.Collection>>()
    val featuredCollectionNames: LiveData<List<com.test.domain.models.images.Collection>> get() = _featuredCollectionNames

    init {
        getFeaturedCollectionNames()
        getCuratedPhotos()
    }


    private fun getFeaturedCollectionNames() {
        viewModelScope.launch {
            val names = loadFeaturedCollectionsUseCase.execute()
            _featuredCollectionNames.postValue(names)
        }
    }


//    fun getImagesFromCollection(collectionId: String) {
//        viewModelScope.launch {
//            val response = imageRepository.getImagesFromCollection(collectionId)
//            imageListFeaturedCollection.postValue(response)
//        }
//    }


    fun getCuratedPhotos() {
        viewModelScope.launch {
            cleanImageRV.postValue(cleanImageRV.value)
            val response = loadCuratedPhotosUseCase.execute()
            imageList.postValue(response)
        }
    }


    fun getImages(query: String) {
        viewModelScope.launch {
            cleanImageRV.postValue(cleanImageRV.value)
            val response = imageRepository.getImages(query)
            imageList.postValue(response)
        }
    }
}