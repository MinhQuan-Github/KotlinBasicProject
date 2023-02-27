package com.example.kotlin_android_project3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo
    var name: String?,

    @ColumnInfo
    var mobile: String?,

    @ColumnInfo
    var email: String?
) {
    fun getID(): Int = id

    @JvmName("getName1")
    fun getName(): String? = name
    @JvmName("setName1")
    fun setName(name: String) { this.name = name }

    @JvmName("getMobile1")
    fun getMobile(): String? = mobile
    @JvmName("setMobile1")
    fun setMobile(mobile: String) { this.mobile = mobile }

    @JvmName("getEmail1")
    fun getEmail(): String? = email
    @JvmName("setEmail1")
    fun setEmail(email: String) { this.email = email }
}