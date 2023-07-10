package com.example.proyectofinal.dataLayer.dataSource.retrofit.services

import com.example.proyectofinal.dataLayer.model.Breeds
import retrofit2.http.GET

interface DogApiService {
    @GET("api/breeds/list/all")
    suspend fun getBreeds(): Breeds

}