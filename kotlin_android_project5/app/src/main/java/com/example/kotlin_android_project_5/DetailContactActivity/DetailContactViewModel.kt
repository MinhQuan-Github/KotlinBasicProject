package com.example.kotlin_android_project_5.DetailContactActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.kotlin_android_project_5.Database.DBHelper
import com.example.kotlin_android_project_5.Model.ContactModel

class DetailContactViewModel: ViewModel() {

    fun getContact(context: Context, id: Int): ContactModel? {
        val db = DBHelper(context)
        return db.getContact(context, id)
    }
}