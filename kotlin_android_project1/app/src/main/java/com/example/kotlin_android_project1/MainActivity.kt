package com.example.kotlin_android_project1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var plusButton: FloatingActionButton? = null
    private var minusButton: FloatingActionButton? = null
    private var multiplyButton: FloatingActionButton? = null
    private var divideButton: FloatingActionButton? = null
    private var resultTextView: TextView? = null
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        this.initUI()
        this.initAction()
        this.observeValue()
    }

    private fun initUI() {
        this.plusButton = findViewById(R.id.btn_plus)
        this.minusButton = findViewById(R.id.btn_minus)
        this.multiplyButton = findViewById(R.id.btn_multiply)
        this.divideButton = findViewById(R.id.btn_divide)
        this.resultTextView = findViewById(R.id.tv_result)
        this.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    private fun initAction() {
        this.plusButton?.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Plus)
        }

        this.minusButton?.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Minus)
        }

        this.multiplyButton?.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Multiply)
        }

        this.divideButton?.setOnClickListener {
            this.viewModel?.updateNumber(Operator.Divide)
        }
    }

    private fun observeValue() {
        this.viewModel?.number?.observe(this) {
            this.resultTextView?.text = it.toString()
        }
    }
}