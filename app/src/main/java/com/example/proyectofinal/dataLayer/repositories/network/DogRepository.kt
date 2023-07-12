package com.example.proyectofinal.dataLayer.repositories.network

import com.example.proyectofinal.dataLayer.dataSource.retrofit.RetrofitInstance
import com.example.proyectofinal.dataLayer.model.Breeds
import com.example.proyectofinal.dataLayer.model.ImgsBreeds

class DogRepository {
    private val dogService = RetrofitInstance.dogBreedsService

    suspend fun getDogBreeds(): Breeds {
        return dogService.getBreeds()
    }
    suspend fun getDogImg(breed: String): ImgsBreeds {
        return dogService.getBreedsImages(breed)
    }

    suspend fun getdogBreedImagesService(breeds:String): ImgsBreeds {
        return dogService.getBreedsImages(breeds = breeds)
    }

    suspend fun getdogBreedsImagesService(breeds:String, hound:String): ImgsBreeds {
        return dogService.getBreedsImages(breeds = breeds, hound = hound)
    }
}