package com.example.kotlin_android_project1

import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_android_project1.databinding.ActivityDetailBinding
import android.os.Bundle

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.initData()
    }

    private fun initData() {
        this.binding.tvDisplay.text = this.intent.getStringExtra("number_display")

    }
}