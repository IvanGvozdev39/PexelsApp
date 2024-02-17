package com.test.domain.repository

import com.test.domain.models.ImageResponse
import com.test.domain.models.FeaturedTitle
import retrofit2.Response

interface ImageRepository {

    suspend fun getImages(query : String): Response<ImageResponse>
    suspend fun getCuratedPhotos(): Response<ImageResponse>
    suspend fun getFeaturedCollectionNames(): List<String>
}