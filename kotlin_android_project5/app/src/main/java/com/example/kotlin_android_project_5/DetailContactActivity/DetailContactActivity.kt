package com.example.kotlin_android_project_5.DetailContactActivity

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.databinding.ActivityDetailContactBinding


class DetailContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailContactBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initData()
        this.initAction()
    }

    private fun initUI() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        this.binding = ActivityDetailContactBinding.inflate(layoutInflater)
        this.setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initData() {
        val model = this.intent.getParcelableExtra("contactModel", ContactModel::class.java)
        if (model != null) {
            this.binding.nameTv.text = model.name.toString()
            this.binding.mobileTv.text = model.mobile.toString()
            this.binding.emailTv.text = model.email.toString()
        }

    }

    private fun initAction() {
        this.binding.backBtn.setOnClickListener {
            finish()
        }
    }
}