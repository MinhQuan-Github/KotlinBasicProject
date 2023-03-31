package com.example.kotlin_android_project_5.EditContactActivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.databinding.ActivityEditContactBinding

class EditContactActivity : AppCompatActivity() {

    private var viewModel: EditContactViewModel? = null
    private lateinit var binding: ActivityEditContactBinding
    private var model: ContactModel? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initAction()
        this.initData()
    }

    private fun initUI() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        this.binding = ActivityEditContactBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(EditContactViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initData() {
        this.model = this.intent.getParcelableExtra("contactModel", ContactModel::class.java)
        if (this.model != null) {
            this.binding.nameEditText.setText(this.model?.name.toString())
            this.binding.phoneEditText.setText(this.model?.mobile.toString())
            this.binding.emailEditText.setText(this.model?.email.toString())
        }
    }

    private fun initAction() {
        this.binding.closeBtn.setOnClickListener {
            this.finish()
        }

        this.binding.saveBtn.setOnClickListener {
            val model_forward: ContactModel = this.model!!
            model_forward.name = this.binding.nameEditText.text.toString()
            model_forward.mobile = this.binding.phoneEditText.text.toString()
            model_forward.email = this.binding.emailEditText.text.toString()
            this.viewModel?.updateContact(this@EditContactActivity, model_forward)
            this.finish()
        }
    }
}