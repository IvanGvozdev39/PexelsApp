package com.test.pexelsapp.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.data.repository.ImageRepositoryImpl
import com.test.domain.use_cases.LoadCuratedPhotosUseCase
import com.test.domain.use_cases.LoadFeaturedCollectionsUseCase

class HomeViewModelFactory : ViewModelProvider.Factory {

    private val imageRepository by lazy(LazyThreadSafetyMode.NONE) {
        ImageRepositoryImpl()
    }

    private val loadFeaturedCollectionsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        LoadFeaturedCollectionsUseCase(imageRepository)
    }

    private val loadCuratedPhotosUseCase by lazy(LazyThreadSafetyMode.NONE) {
        LoadCuratedPhotosUseCase(imageRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            imageRepository,
            loadFeaturedCollectionsUseCase,
            loadCuratedPhotosUseCase
        ) as T
    }
}