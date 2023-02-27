package com.example.kotlin_android_project4.ViewModel

import androidx.lifecycle.ViewModel
import com.example.kotlin_android_project4.Extension.Extension

class MainActivityViewModel: ViewModel() {
    var queueCalculator: ArrayList<String>? = null
    var status: Int = 0

    init {
        this.queueCalculator = ArrayList<String>()
        this.queueCalculator!!.add("")
        this.queueCalculator!!.add("")
        this.queueCalculator!!.add("")
    }
    data class Calculate(val history: String, val result: String)
    fun calculate(): Calculate {
        val a_int = this.queueCalculator?.get(0)?.toDouble() ?: 0.0
        val operator = this.queueCalculator?.get(1) ?: "Plus"
        val b_int = this.queueCalculator?.get(2)?.toDouble() ?: 0.0

        if (operator == Extension.Operator.Plus.toString()) {
            return Calculate("$a_int + $b_int = ${a_int + b_int}", "${a_int + b_int}")
        } else if (operator == Extension.Operator.Minus.toString()) {
            return Calculate("$a_int - $b_int = ${a_int - b_int}", "${a_int - b_int}")
        } else if (operator == Extension.Operator.Multiply.toString()) {
            return Calculate("$a_int * $b_int = ${a_int * b_int}", "${a_int * b_int}")
        } else if (operator == Extension.Operator.Divide.toString()) {
            return if (b_int == 0.0) {
                Calculate("$a_int / 0 = NaN", "NaN")
            } else {
                Calculate("$a_int / $b_int = ${a_int / b_int}", "${a_int / b_int}")
            }
        }
        return Calculate("", "")
    }
}