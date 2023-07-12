package com.example.proyectofinal.dataLayer.dataSource.retrofit.services

import com.example.proyectofinal.dataLayer.model.Breeds
import com.example.proyectofinal.dataLayer.model.ImgsBreeds
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    @GET("api/breeds/list/all")
    suspend fun getBreeds(): Breeds

    @GET("api/breed/{breeds}/images/random/3")
    suspend fun getImagesBreeds(
        @Path("breeds") breeds:String,
    ): ImgsBreeds

    @GET("api/breed/{breeds}/{hound}/images/random/3")
    suspend fun getImagesBreedsHound(
        @Path("breeds") breeds:String,
        @Path("hound") hound:String
    ): ImgsBreeds

}