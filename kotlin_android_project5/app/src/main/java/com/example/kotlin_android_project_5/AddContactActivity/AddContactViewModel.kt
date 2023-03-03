package com.example.kotlin_android_project_5.AddContactActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.Database.DBHelper

class AddContactViewModel: ViewModel() {

    fun addContact(context: Context, contact: ContactModel) {
        val db = DBHelper(context)
        db.addNewContact(context, contact)
    }
}