package com.example.kotlin_android_project3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
class ContactModel(
    @PrimaryKey(autoGenerate = true)
    private var id: Int,

    @ColumnInfo
    private var name: String,

    @ColumnInfo
    private var mobile: String,

    @ColumnInfo
    private var email: String
) {
    fun getID(): Int = id
    fun setID(id: Int) { this.id = id }

    fun getName(): String = name
    fun setName(name: String) { this.name = name }

    fun getMobile(): String = mobile
    fun setMobile(mobile: String) { this.mobile = mobile }

    fun getEmail(): String = email
    fun setEmail(email: String) { this.email = email }

}