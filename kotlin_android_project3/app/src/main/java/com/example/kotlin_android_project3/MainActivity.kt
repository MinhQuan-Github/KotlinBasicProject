package com.example.kotlin_android_project3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_android_project3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var contactLists: ArrayList<ContactModel>? = null
    private var contactAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUI() {
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)

        this.binding.rvContact.layoutManager = LinearLayoutManager(this)
        this.contactLists = ArrayList()
        this.contactAdapter = ContactAdapter(contactLists!!)
        this.binding.rvContact.adapter = this.contactAdapter

        this.contactLists?.add(ContactModel(1, "hi", "", ""))
        this.contactLists?.add(ContactModel(2, "abc", "", ""))
        this.contactLists?.add(ContactModel(3, "11", "", ""))
        this.contactLists?.add(ContactModel(4, "f", "", ""))
        this.contactAdapter?.notifyDataSetChanged()

        val db = AppDatabase.getInstance(applicationContext)
        val contactDao = db.contactDao()
        val allContact = contactDao.getAll()
//        this.contactDao!!.insert(ContactModel(5, "", "", ""))
    }


}