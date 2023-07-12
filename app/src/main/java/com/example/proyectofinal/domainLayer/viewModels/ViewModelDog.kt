package com.example.proyectofinal.domainLayer.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.dataLayer.model.Dog
import com.example.proyectofinal.dataLayer.repositories.db.DogRepositoryRoom
import com.example.proyectofinal.dataLayer.repositories.network.DogRepository
import com.example.proyectofinal.domainLayer.utils.convertResponseApiToBreedsList
import com.example.proyectofinal.domainLayer.utils.dogToDogEntity
import com.example.proyectofinal.domainLayer.utils.dogsEntityToDogs
import com.example.proyectofinal.domainLayer.utils.stringImageOrStringsImages
import com.example.proyectofinal.domainLayer.utils.stringsImagesTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ViewModelDog(var roomRepository: DogRepositoryRoom) : ViewModel() {

    //Network
    private val repository = DogRepository()

    private val _dogBreeds = MutableLiveData<List<String>>()
    val dogBreeds: LiveData<List<String>?> = _dogBreeds

    //DB
    private val _roomDogBreeds = MutableLiveData<List<String>>()
    val roomDogBreeds: LiveData<List<String>?> = _roomDogBreeds

    private val _dogsDetail = MutableLiveData<List<String>>()
    val dogsDetail: LiveData<List<String>> = _dogsDetail

    fun getDogBreeds() {
        viewModelScope.launch {
            try {
                val breeds = repository.getDogBreeds()
                _dogBreeds.value = convertResponseApiToBreedsList(breeds.message)
                val dogs = convertResponseApiToBreedsList(breeds.message)
            } catch (e: Exception) {
                // Handle error
                Log.d("LUAa", "getDogBreeds: ${e.message}")
            }
        }
    }

    fun getDogBreedImages(breed:String){
        viewModelScope.launch {
            try {
                if (stringImageOrStringsImages(breed)){
                    val images = repository.getdogBreedImagesService(breed).message
                    _dogsDetail.value = images
                }else{
                    val transformation = stringsImagesTransformation(breed)
                    val images = repository.getdogBreedsImagesService(transformation.breeds,transformation.hound).message
                    _dogsDetail.value = images
                }

            }catch (e: Exception) {
                Log.e("getDogBreedImages", e.message.toString());
            }

        }
    }

    fun getRoomAllDogs(){
        viewModelScope.launch(Dispatchers.Main) {
            val data = dogsEntityToDogs(roomRepository.allDogs.first())
            _roomDogBreeds.value = data.map { it.breed }
        }

    }

    fun insertRoomDog(dog: Dog){
        viewModelScope.launch(Dispatchers.IO){
            roomRepository.insert(dogToDogEntity(dog))
        }
        getRoomAllDogs()
    }

    fun deleteRoomDog(dog: Dog){
        viewModelScope.launch(Dispatchers.IO){
            roomRepository.delete(dog.breed)
        }
        getRoomAllDogs()
    }
}

class ViewModelFactory(private val repository: DogRepositoryRoom) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDog::class.java)) {
            return ViewModelDog(repository) as T
        }
        throw IllegalArgumentException("Ocurrio un error")
    }
}
