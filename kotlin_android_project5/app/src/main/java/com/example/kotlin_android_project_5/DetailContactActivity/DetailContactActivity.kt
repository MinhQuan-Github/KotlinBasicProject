package com.example.kotlin_android_project_5.DetailContactActivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project_5.EditContactActivity.EditContactActivity
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.databinding.ActivityDetailContactBinding


class DetailContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailContactBinding
    private var viewModel: DetailContactViewModel? = null
    private var model: ContactModel? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)

    override fun onResume() {
        super.onResume()
        this.initData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initAction()
    }

    private fun initUI() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        this.binding = ActivityDetailContactBinding.inflate(layoutInflater)
        this.viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(DetailContactViewModel::class.java)
        this.setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initData() {
        val id = this.intent.getParcelableExtra("contactModel", ContactModel::class.java)?.id
        this.model = this.viewModel?.getContact(this@DetailContactActivity, id?: 0)
        if (this.model != null) {
            this.binding.nameTv.text = this.model?.name.toString()
            this.binding.mobileTv.text = this.model?.mobile.toString()
            this.binding.emailTv.text = this.model?.email.toString()
        }

    }

    private fun initAction() {
        this.binding.backBtn.setOnClickListener {
            finish()
        }

        this.binding.editBtn.setOnClickListener {
            val intent = Intent(this@DetailContactActivity, EditContactActivity::class.java)
            intent.putExtra("contactModel", this.model)
            this@DetailContactActivity.startActivity(intent)
        }
    }
}