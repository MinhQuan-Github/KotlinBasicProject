package com.example.kotlin_android_project3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "mobile")
    var mobile: String?,

    @ColumnInfo(name = "email")
    var email: String?
)