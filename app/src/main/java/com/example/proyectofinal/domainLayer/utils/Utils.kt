package com.example.proyectofinal.domainLayer.utils

import com.example.proyectofinal.dataLayer.entities.DogEntity
import com.example.proyectofinal.dataLayer.model.Dog
import com.example.proyectofinal.dataLayer.model.TransformationDog


fun convertResponseApiToBreedsList(list: Map<String, List<String>>?):List<String> {
    val dogs : ArrayList<String> = arrayListOf()
    list?.forEach { (s, strings) ->
        dogs.add(s)
        if (strings.isNotEmpty()){
            strings.forEach {
                dogs.add(s.plus("-".plus(it)))
            }
        }
    }
    return dogs
}
fun stringImageOrStringsImages(string: String):Boolean{
    return !string.contains("-")
}

fun stringsImagesTransformation(string: String): TransformationDog {
    val transformationDog = string.split("-")
    return TransformationDog(
        breeds = transformationDog.firstOrNull()?:"",
        hound = transformationDog.lastOrNull()?:""
    )
}

fun dogToDogEntity(dog: Dog):DogEntity{
    return DogEntity(breed = dog.breed, img = dog.image)

}

fun dogsEntityToDogs(dogs: List<DogEntity>):ArrayList<Dog>{
    val arrayList:ArrayList<Dog> = arrayListOf()
    dogs.forEach {
        arrayList.add(Dog(breed = it.breed, image = it.img))
    }
    return arrayList

}