package com.example.kotlin_android_project1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null
    private lateinit var binding: ActivityMainBinding
    private var arrayList: ArrayList<Int>? = null
    private var arrayAdapter: ArrayAdapter<Int>? = null

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
        this.arrayList = ArrayList()
        this.arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, this.arrayList!!)
        this.binding.listView.adapter = this.arrayAdapter
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

        this.binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            this.arrayList?.removeAt(position)
            this.arrayAdapter?.notifyDataSetChanged()
            true
        }

        this.binding.listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("number_display", this.arrayList?.get(position).toString())
            this.startActivity(intent)
        }
    }

    private fun observeValue() {
        this.viewModel?.number?.observe(this) {
            this.binding.tvResult.text = it.toString()
            this.arrayList?.add(it)
            this.arrayAdapter?.notifyDataSetChanged()
        }
    }
}