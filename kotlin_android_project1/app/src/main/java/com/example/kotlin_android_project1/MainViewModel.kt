package com.example.kotlin_android_project1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var number = MutableLiveData<Int>()

    init {
        this.number.value = 0
    }

    fun updateNumber(operator: Operator) {
        print(this.number.value)
        if (operator == Operator.Plus) {
            this.number.value = (this.number.value)?.plus(1)
        } else if (operator == Operator.Minus) {
            this.number.value = (this.number.value)?.minus(1)
        } else if (operator == Operator.Multiply) {
            this.number.value = (this.number.value)?.times(2)
        } else if (operator == Operator.Divide) {
            this.number.value = (this.number.value)?.div(2)
        }
    }
}

