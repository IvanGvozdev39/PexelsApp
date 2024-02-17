package com.test.domain.repository

import com.test.domain.models.images.CollectionMediaResponse
import com.test.domain.models.images.ImageResponse
import retrofit2.Response

interface ImageRepository {

    suspend fun getImages(query : String): Response<ImageResponse>
    suspend fun getCuratedPhotos(): Response<ImageResponse>
    suspend fun getFeaturedCollectionNames(): List<com.test.domain.models.images.Collection>
    suspend fun getImagesFromCollection(collectionId: String): Response<CollectionMediaResponse>
}