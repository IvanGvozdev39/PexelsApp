package com.test.domain.use_cases

import com.test.domain.models.images.ImageResponse
import com.test.domain.repository.ImageRepository
import retrofit2.Response

class SearchUseCase(private val imageRepository: ImageRepository) {

    suspend fun execute(query: String): Response<ImageResponse> {
        return imageRepository.getImages(query)
    }
}