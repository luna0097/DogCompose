package com.example.proyectofinal.dataLayer.model

data class Breeds(
    val message: Map<String, List<String>>,
    val status: String,
)

data class ImgsBreeds(
    val message: List<String>,
    val status: String,
)

data class Dog(
    val breed: String,
    val image: String ="",
    var favorite:Boolean=false
)

data class TransformationDog(
    val breeds :String = "",
    val hound:String = ""
)

