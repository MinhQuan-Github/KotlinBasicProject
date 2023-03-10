package com.example.kotlin_android_project6.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogsApi {
    @GET("/DevTides/DogsApi/master/dogs.json")
    fun getDog(): Single<List<DogFreedModel>>


}