package com.example.proyectofinal.domainLayer.utils


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