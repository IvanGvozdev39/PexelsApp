package com.test.domain.api

import com.test.domain.models.FeaturedCollectionsResponse
import com.test.domain.models.ImageResponse
import com.test.domain.models.FeaturedTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("Authorization: dl4SeUa1OfAeZQqkN160kyujsExbgUz5qLd621n6hGVQD2FdISfBM11D")
    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 7
    ): Response<FeaturedCollectionsResponse>


    @Headers("Authorization: dl4SeUa1OfAeZQqkN160kyujsExbgUz5qLd621n6hGVQD2FdISfBM11D")
    @GET("search")
    suspend fun getImages(
        @Query("query") query : String,
        @Query("per_page") perpage : Int = 80,
    ): Response<ImageResponse>


    @Headers("Authorization: dl4SeUa1OfAeZQqkN160kyujsExbgUz5qLd621n6hGVQD2FdISfBM11D")
    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): Response<ImageResponse>
}