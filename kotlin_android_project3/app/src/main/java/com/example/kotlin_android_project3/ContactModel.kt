package com.example.kotlin_android_project3

class ContactModel(
    private var name: String,
    private var mobile: String,
    private var email: String
) {
    fun getName(): String = name
    fun setName(name: String) { this.name = name }

    fun getMobile(): String = mobile
    fun setMobile(mobile: String) { this.mobile = mobile }

    fun getEmail(): String = email
    fun setEmail(email: String) { this.email = email }

}