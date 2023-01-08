package com.example.kotlin_android_project1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var plusButton: FloatingActionButton? = null
    private var minusButton: FloatingActionButton? = null
    private var resultTextView: TextView? = null
    private var count: Int? = null

    init {
        this.count = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        this.initUI()
        this.initAction()
    }

    private fun initUI() {
        this.plusButton = findViewById(R.id.btn_plus)
        this.minusButton = findViewById(R.id.btn_minus)
        this.resultTextView = findViewById(R.id.tv_result)
        this.resultTextView?.text = "${this.count}"
    }

    @SuppressLint("SetTextI18n")
    private fun initAction() {
        this.plusButton?.setOnClickListener {
            this.resultTextView?.text = "${this.count!! + 1}"
            this.count = this.count!! + 1;
        }

        this.minusButton?.setOnClickListener {
            this.resultTextView?.text = "${this.count!! - 1}"
            this.count = this.count!! - 1;
        }
    }
}