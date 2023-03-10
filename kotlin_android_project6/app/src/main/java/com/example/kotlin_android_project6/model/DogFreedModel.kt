package com.example.kotlin_android_project6.model

import com.google.gson.annotations.SerializedName

class DogFreedModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("life_span")
    var lifeSpan: String = ""

    @SerializedName("origin")
    var origin: String = ""

    @SerializedName("url")
    var url: String = ""

}

