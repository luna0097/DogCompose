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
        return dogService.getImagesBreeds(breed)
    }

    suspend fun getDogBreedImagesService(breeds:String): ImgsBreeds {
        return dogService.getImagesBreeds(breeds = breeds)
    }

    suspend fun getDogBreedsImagesService(breeds:String, hound:String): ImgsBreeds {
        return dogService.getImagesBreedsHound(breeds = breeds, hound = hound)
    }
}