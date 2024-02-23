package com.test.pexelsapp.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.data.repository.ImageRepositoryImpl
import com.test.domain.use_cases.LoadCuratedImagesUseCase
import com.test.domain.use_cases.LoadFeaturedCollectionsUseCase
import com.test.domain.use_cases.LoadPopularImagesUseCase
import com.test.domain.use_cases.SearchUseCase

class HomeViewModelFactory : ViewModelProvider.Factory {

    private val imageRepository by lazy(LazyThreadSafetyMode.NONE) {
        ImageRepositoryImpl()
    }

    private val loadFeaturedCollectionsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        LoadFeaturedCollectionsUseCase(imageRepository)
    }

    private val loadCuratedPhotosUseCase by lazy(LazyThreadSafetyMode.NONE) {
        LoadCuratedImagesUseCase(imageRepository)
    }

    private val loadPopularImagesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        LoadPopularImagesUseCase(imageRepository)
    }

    private val searchUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SearchUseCase(imageRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                imageRepository,
                loadFeaturedCollectionsUseCase,
                loadCuratedPhotosUseCase,
                loadPopularImagesUseCase,
                searchUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}