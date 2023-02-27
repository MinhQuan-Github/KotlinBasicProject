package com.example.kotlin_android_project4

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_android_project4.Extension.Extension
import com.example.kotlin_android_project4.ViewModel.MainActivityViewModel
import com.example.kotlin_android_project4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewModel: MainActivityViewModel? = null
    private lateinit var binding: ActivityMainBinding
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initAction()
    }

    private fun initUI() {
        this.sharedPreferences = this.getSharedPreferences("share_pref", Context.MODE_PRIVATE)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        this.binding.historyTextView.text = this.sharedPreferences?.getString("history",null)
    }

    private fun initAction() {
        this.plusAction()
        this.minusAction()
        this.multiplyAction()
        this.divideAction()
        this.deleteAction()
        this.equalsAction()
        this.digitAction()
    }

    private fun resetQueue(element: String) {
        this.viewModel?.queueCalculator?.set(0, element)
        this.viewModel?.queueCalculator?.set(1, "")
        this.viewModel?.queueCalculator?.set(2, "")
    }

    private fun plusAction() {
        this.binding.plusBtn.setOnClickListener {
            this.viewModel?.status = 1
            if (this.viewModel?.queueCalculator?.get(0)?.isEmpty() == true) {
                this.showToast()
            } else {
                this.viewModel?.queueCalculator?.set(1, Extension.Operator.Plus.toString())
            }
        }
    }

    private fun minusAction() {
        this.binding.minusBtn.setOnClickListener {
            this.viewModel?.status = 1
            if (this.viewModel?.queueCalculator?.get(0)?.isEmpty() == true) {
                this.showToast()
            } else {
                this.viewModel?.queueCalculator?.set(1, Extension.Operator.Minus.toString())
            }
        }
    }

    private fun multiplyAction() {
        this.binding.multiplyBtn.setOnClickListener {
            this.viewModel?.status = 1
            if (this.viewModel?.queueCalculator?.get(0)?.isEmpty() == true) {
                this.showToast()
            } else {
                this.viewModel?.queueCalculator?.set(1, Extension.Operator.Multiply.toString())
            }
        }
    }

    private fun divideAction() {
        this.binding.divideBtn.setOnClickListener {
            this.viewModel?.status = 1
            if (this.viewModel?.queueCalculator?.get(0)?.isEmpty() == true) {
                this.showToast()
            } else {
                this.viewModel?.queueCalculator?.set(1, Extension.Operator.Divide.toString())
            }
        }
    }

    private fun deleteAction() {
        this.binding.CBtn.setOnClickListener {
            this.resetQueue("")
            this.binding.resultTextView.text = ""
            this.viewModel?.status = 0
        }
    }

    private fun equalsAction() {
        this.binding.equalBtn.setOnClickListener {
            if (
                this.viewModel?.queueCalculator?.get(0)?.isEmpty() == true ||
                this.viewModel?.queueCalculator?.get(1)?.isEmpty() == true ||
                this.viewModel?.queueCalculator?.get(2)?.isEmpty() == true
            ) {
                this.showToast()
            } else {
                this.viewModel?.status = 0
                val result = this.viewModel?.calculate()
                if (result != null) {
                    this.binding.resultTextView.text = result.result
                    this.binding.historyTextView.text = result.history
                    this.saveData(result.history)
                    this.resetQueue(result.result)
                }
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveData(history: String) {
        val editor = this.sharedPreferences?.edit()
        editor?.putString("history", history)
        editor?.apply()
    }

    @SuppressLint("SetTextI18n")
    private fun digitAction() {
        val digitsButton = arrayListOf<Button>(
            this.binding.zeroBtn, this.binding.oneBtn,
            this.binding.twoBtn, this.binding.threeBtn,
            this.binding.fourBtn, this.binding.fiveBtn,
            this.binding.sixBtn, this.binding.sevenBtn,
            this.binding.eightBtn, this.binding.nineBtn
        )
        digitsButton.forEach { button ->
            button.setOnClickListener {
                if (this.viewModel?.status == 0 || this.viewModel?.status == 2) {
                    val temp: String = this.binding.resultTextView.text as String
                    this.binding.resultTextView.text = temp + button.text
                    this.viewModel?.queueCalculator?.set(this.viewModel?.status!!, temp + button.text)
                } else {
                    this.viewModel?.status = 2
                    this.binding.resultTextView.text = button.text
                    this.viewModel?.queueCalculator?.set(this.viewModel?.status!!, button.text as String)
                }
            }
        }
    }

    private fun showToast() {
        Toast.makeText(this@MainActivity,
            "No input, please enter input",
            Toast.LENGTH_SHORT
        ).show()
    }
}