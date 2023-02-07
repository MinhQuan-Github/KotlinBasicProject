package com.example.kotlin_android_project2

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project2.Extension.Extension
import com.example.kotlin_android_project2.ViewModel.MainActivityViewModel
import com.example.kotlin_android_project2.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private var viewModel: MainActivityViewModel? = null
    private lateinit var binding: ActivityMainBinding
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initAction()
        this.observeValue()
    }

    private fun loadData() {
        val gson = Gson()
        val json = this.sharedPreferences?.getString("history",null)
        val type = object : TypeToken<ArrayList<String>>(){}.type
        this.viewModel?.setResult(gson.fromJson(json,type) ?: ArrayList())
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveData() {
        val editor = this.sharedPreferences?.edit()
        val json = Gson().toJson(this.viewModel?.getResult())
        editor?.putString("history", json)
        editor?.apply()
    }

    private fun initUI() {
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.sharedPreferences = this.getSharedPreferences("share_pref", Context.MODE_PRIVATE)
        this.viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        this.loadData()
        this.arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            this.viewModel?.getResult() ?: ArrayList<String>()
        )
        this.binding.lvStore.adapter = this.arrayAdapter
    }

    @SuppressLint("ServiceCast")
    private fun initAction() {
        this.setActionButton(this.binding.btnPlus, Extension.Operator.Plus)
        this.setActionButton(this.binding.btnMinus, Extension.Operator.Minus)
        this.setActionButton(this.binding.btnMultiply, Extension.Operator.Multiply)
        this.setActionButton(this.binding.btnDivide, Extension.Operator.Divide)

        this.binding.editTextA.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                this.binding.lvStore.clearFocus()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun setActionButton(button: Button, operator: Extension.Operator) {
        button.setOnClickListener {
            this.dismissKeyboard()
            if (this.checkEmpty()) {
                this.showToast()
            } else {
                this.viewModel?.calculate(
                    this.binding.editTextA.text.toString(),
                    this.binding.editTextB.text.toString(),
                    operator
                )
                this.arrayAdapter?.notifyDataSetChanged()
                this.saveData()
            }
        }
    }

    private fun observeValue() {
        this.viewModel?.historyResult?.observe(this) {
            this.arrayAdapter?.notifyDataSetChanged()
        }
    }

    private fun checkEmpty(): Boolean {
        // true -> ERROR
        // false -> OK
        return this.binding.editTextA.text.isEmpty() || this.binding.editTextB.text.isEmpty()
    }

    private fun dismissKeyboard() {
        this.hideKeyboard(this.binding.editTextA)
        this.hideKeyboard(this.binding.editTextB)
    }

    private fun showToast() {
        Toast.makeText(this@MainActivity,
            "Attention: a and b is not empty, please re-enter",
            Toast.LENGTH_SHORT
        ).show()
    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}