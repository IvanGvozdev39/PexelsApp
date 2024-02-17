package com.test.domain.use_cases

import com.test.domain.repository.ImageRepository

class LoadFeaturedCollectionsUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute(): List<String> {
        return imageRepository.getFeaturedCollectionNames()
    }
}