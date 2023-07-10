package com.example.proyectofinal.dataLayer.repositories.network

import com.example.proyectofinal.dataLayer.dataSource.retrofit.RetrofitInstance
import com.example.proyectofinal.dataLayer.model.Breeds

class DogRepository {
    private val creditCardService = RetrofitInstance.dogBreedsService

    suspend fun getDogBreeds(): Breeds {
        return creditCardService.getBreeds()
    }
}