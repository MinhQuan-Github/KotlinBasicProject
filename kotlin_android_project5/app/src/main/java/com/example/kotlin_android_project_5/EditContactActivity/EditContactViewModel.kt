package com.example.kotlin_android_project_5.EditContactActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.kotlin_android_project_5.Database.DBHelper
import com.example.kotlin_android_project_5.Model.ContactModel

class EditContactViewModel: ViewModel() {

    fun updateContact(context: Context, contact: ContactModel) {
        val db = DBHelper(context)
        db.updateContact(context, contact)
    }
}