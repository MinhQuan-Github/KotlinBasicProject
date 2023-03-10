package com.example.kotlin_android_project6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlin_android_project6.model.DogFreedModel
import com.example.kotlin_android_project6.viewmodel.DogApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var apiService: DogApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.observeValueChanged()
    }

    private fun observeValueChanged() {
        this.apiService = DogApiService
        this.apiService?.getDogs()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object: DisposableSingleObserver<List<DogFreedModel>>() {
                override fun onSuccess(dogFreed: List<DogFreedModel>) {
                    Log.d("DEBUG", "onSuccess DogFreedModel")
                }

                override fun onError(e: Throwable) {
                    Log.d("DEBUG", "onError DogFreedModel")
                }
            })
    }

}