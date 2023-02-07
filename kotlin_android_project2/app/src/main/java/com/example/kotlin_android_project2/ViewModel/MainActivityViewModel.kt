package com.example.kotlin_android_project2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_android_project2.Extension.Extension

class MainActivityViewModel: ViewModel() {
    var historyResult = MutableLiveData<ArrayList<String>>()

    init {
        this.historyResult.value = ArrayList()
    }

    fun calculate(a: String, b: String, operator: Extension.Operator) {
        val a_int = Integer.parseInt(a)
        val b_int = Integer.parseInt(b)
        if (operator == Extension.Operator.Plus) {
            this.historyResult.value?.add(0, "$a + $b = ${a_int + b_int}")
        } else if (operator == Extension.Operator.Minus) {
            this.historyResult.value?.add(0, "$a - $b = ${a_int - b_int}")
        } else if (operator == Extension.Operator.Multiply) {
            this.historyResult.value?.add(0, "$a * $b = ${a_int * b_int}")
        } else if (operator == Extension.Operator.Divide) {
            if (b_int == 0) {
                this.historyResult.value?.add(0, "$a / $b = NaN")
            } else {
                this.historyResult.value?.add(0, "$a / $b = ${a_int * 1.0 / b_int}")
            }
        }
    }

    fun setResult(result: ArrayList<String>) {
        this.historyResult.value = result
    }

    fun getResult(): ArrayList<String>? {
        return this.historyResult.value
    }
}