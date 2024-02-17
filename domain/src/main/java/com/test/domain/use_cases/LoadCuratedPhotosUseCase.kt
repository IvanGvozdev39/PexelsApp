package com.test.domain.use_cases

import com.test.domain.models.images.ImageResponse
import com.test.domain.repository.ImageRepository
import retrofit2.Response

class LoadCuratedPhotosUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute(): Response<ImageResponse> {
        return imageRepository.getCuratedPhotos()
    }
}