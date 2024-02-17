package com.test.data.repository

import com.test.domain.api.RetrofitInstance
import com.test.domain.api.RetrofitInstance.Companion.api
import com.test.domain.models.images.CollectionMediaResponse
import com.test.domain.models.images.ImageResponse
import com.test.domain.repository.ImageRepository
import retrofit2.Response

class ImageRepositoryImpl : ImageRepository {

    override suspend fun getImages(query : String) = RetrofitInstance.api.getImages(query)


    override suspend fun getCuratedPhotos(): Response<ImageResponse> {
        return api.getCuratedPhotos()
    }

    override suspend fun getFeaturedCollectionNames(): List<com.test.domain.models.images.Collection> {
        return api.getFeaturedCollections().body()?.collections ?: emptyList()
//        return response.body()?.collections?.map { it.title } ?: emptyList()
    }

    override suspend fun getImagesFromCollection(collectionId: String): Response<ImageResponse> {
        return api.getImagesFromCollection(collectionId)
    }
}