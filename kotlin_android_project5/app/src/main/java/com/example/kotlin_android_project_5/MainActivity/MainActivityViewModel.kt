package com.example.kotlin_android_project_5.MainActivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.Database.DBHelper


class MainActivityViewModel: ViewModel() {

    private val _contactLists = MutableLiveData<List<ContactModel>>()
    val contactLists: LiveData<List<ContactModel>> = _contactLists

    fun loadAllContacts(context: Context) {
        val db = DBHelper(context)
        _contactLists.value = db.getAllContacts(context)
    }

    fun getContact(context: Context, name: String) {
        val db = DBHelper(context)
        _contactLists.value = db.getContact(context, name)
    }
}