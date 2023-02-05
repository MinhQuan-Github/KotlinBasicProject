package com.example.kotlin_android_project1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.initUI()
        this.initAction()
        this.observeValue()
    }

    private fun initUI() {
        this.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    private fun initAction() {
        this.binding.btnPlus.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Plus)
        }

        this.binding.btnMinus.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Minus)
        }

        this.binding.btnMultiply.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Multiply)
        }

        this.binding.btnDivide.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Divide)
        }
    }

    private fun observeValue() {
        this.viewModel?.number?.observe(this) {
            this.binding.tvResult.text = it.toString()
        }
    }
}