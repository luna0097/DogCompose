package com.example.proyectofinal.domainLayer.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.dataLayer.model.Breeds
import com.example.proyectofinal.dataLayer.repositories.network.DogRepository
import com.example.proyectofinal.domainLayer.utils.convertResponseApiToBreedsList
import kotlinx.coroutines.launch

class ViewModelDog : ViewModel() {
    private val repository = DogRepository()

    private val _dogBreeds = MutableLiveData<List<String>>()
    val dogBreeds: LiveData<List<String>?> = _dogBreeds


    private val _roomDogBreeds = MutableLiveData<List<String>>()
    val roomDogBreeds: LiveData<List<String>?> = _roomDogBreeds

    fun getDogBreeds() {
        viewModelScope.launch {
            try {
                val breeds = repository.getDogBreeds()
                Log.d("LUAA", "getDogBreeds: $breeds")
                _dogBreeds.value = convertResponseApiToBreedsList(breeds.message)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}