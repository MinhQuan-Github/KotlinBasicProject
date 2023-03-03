package com.example.kotlin_android_project3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {

    @Query(value = "SELECT * FROM Contact")
    fun getAll(): List<ContactModel>

    @Insert
    fun insert(vararg contact: ContactModel)

}