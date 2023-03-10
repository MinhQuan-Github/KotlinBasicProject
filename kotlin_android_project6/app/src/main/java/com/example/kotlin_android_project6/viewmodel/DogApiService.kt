package com.example.kotlin_android_project6.viewmodel

import com.example.kotlin_android_project6.model.DogFreedModel
import com.example.kotlin_android_project6.model.DogsApi
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DogApiService {
    object URL {
        var BASE_URL = "https://raw.githubusercontent.com"
    }
    private var retrofit: Retrofit? = null
    val apiService: DogsApi
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(URL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!.create(DogsApi::class.java)
        }

    fun getDogs(): Single<List<DogFreedModel>> {
        return apiService.getDog()
    }
}

