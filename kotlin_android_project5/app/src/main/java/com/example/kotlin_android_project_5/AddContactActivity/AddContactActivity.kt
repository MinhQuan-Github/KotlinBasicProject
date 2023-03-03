package com.example.kotlin_android_project_5.AddContactActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.databinding.ActivityAddContactBinding

class AddContactActivity : AppCompatActivity() {

    private var viewModel: AddContactViewModel? = null
    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initAction()
    }

    private fun initUI() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        this.binding = ActivityAddContactBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(AddContactViewModel::class.java)
    }

    private fun initAction() {
        this.binding.closeBtn.setOnClickListener {
            this.finish()
        }

        this.binding.saveBtn.setOnClickListener {
            val model = ContactModel(
                name = this.binding.editNameTv.text.toString(),
                mobile = this.binding.editMobileTv.text.toString(),
                email = this.binding.editEmailTv.text.toString()
            )
            this.viewModel?.addContact(this@AddContactActivity, model)
            this.finish()
        }
    }
}
