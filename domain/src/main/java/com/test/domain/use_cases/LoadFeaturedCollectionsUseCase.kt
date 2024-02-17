package com.test.domain.use_cases

import com.test.domain.repository.ImageRepository

class LoadFeaturedCollectionsUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute(): List<com.test.domain.models.images.Collection> {
        return imageRepository.getFeaturedCollectionNames()
    }
}