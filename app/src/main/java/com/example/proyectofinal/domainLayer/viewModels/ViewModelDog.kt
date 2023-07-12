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
import com.example.proyectofinal.domainLayer.utils.apiResponseToBreedsList
import com.example.proyectofinal.domainLayer.utils.dogModelToDogEntity
import com.example.proyectofinal.domainLayer.utils.dogsEntityToDogsModelList
import com.example.proyectofinal.domainLayer.utils.stringImgOrStringsImgs
import com.example.proyectofinal.domainLayer.utils.stringsImgsTransfor
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
                _dogBreeds.value = apiResponseToBreedsList(breeds.message)
            } catch (e: Exception) {
                // Handle error
                Log.d("LUAa", "getDogBreeds: ${e.message}")
            }
        }
    }

    fun getDogBreedImages(breed:String){
        viewModelScope.launch {
            try {
                if (stringImgOrStringsImgs(breed)){
                    val images = repository.getDogBreedImagesService(breed).message
                    _dogsDetail.value = images
                }else{
                    val transformation = stringsImgsTransfor(breed)
                    val images = repository.getDogBreedsImagesService(transformation.breeds,transformation.hound).message
                    _dogsDetail.value = images
                }

            }catch (e: Exception) {
                Log.e("getDogBreedImages", e.message.toString());
            }

        }
    }

    fun getRoomAllDogs(){
        viewModelScope.launch(Dispatchers.Main) {
            val data = dogsEntityToDogsModelList(roomRepository.allDogs.first())
            _roomDogBreeds.value = data.map { it.breed }
        }

    }

    fun insertRoomDog(dog: Dog){
        viewModelScope.launch(Dispatchers.IO){
            roomRepository.insert(dogModelToDogEntity(dog))
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
