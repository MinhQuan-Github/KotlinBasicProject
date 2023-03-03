package com.example.kotlin_android_project_5.MainActivity

import android.content.Context
import com.example.kotlin_android_project_5.Model.ContactModel

interface DBService {
    fun getAllContacts(context: Context): List<ContactModel>
    fun addNewContact(context: Context, contact: ContactModel)
    fun updateContact(context: Context, contact: ContactModel): Int
    fun removeContact(context: Context, contact: ContactModel)
    fun getContact(context: Context, name: String): List<ContactModel>
}
